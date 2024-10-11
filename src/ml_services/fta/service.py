import json
import logging
import sys
import signal
import argparse
from flask import Flask, Response, request, jsonify
from tempfile import NamedTemporaryFile
from scram_proxy import ScramProxy
from tornado.wsgi import WSGIContainer
from tornado.httpserver import HTTPServer
from tornado.ioloop import IOLoop


parser = argparse.ArgumentParser(description=
"""Service, that validates and predicts fault probability based on data in incoming xml. 
endpoints:
fta/validate - validate xml;
fta/probability - predict fault probability;
health - returns service status
""")
parser.add_argument("-p", "--port", type=int, default=5003, help="Service will listen to this port.")
parser.add_argument("-l", "--log_info", action="store_true", help="Defines logging level. Saves info logs, if specified.")
args = parser.parse_args()

if args.log_info:
    logging_level = logging.INFO
else:
    logging_level = logging.ERROR


logging.basicConfig(level=logging_level, 
format='%(asctime)s.%(msecs)03d %(levelname)s in %(module)s - function name: %(funcName)s; msg: %(message)s',
    datefmt='%Y-%m-%d %H:%M:%S',)
logger = logging.getLogger("service")


class InvalidUsage(Exception):
    status_code = 400

    def __init__(self, message, status_code=None, payload=None):
        Exception.__init__(self)
        self.message = message
        if status_code is not None:
            self.status_code = status_code
        self.payload = payload

    def to_dict(self):
        rv = dict(self.payload or ())
        rv['message'] = self.message
        return rv

app = Flask(__name__)

def save_data_to_tmp(data, name_prefix):
    input_file = NamedTemporaryFile(prefix=name_prefix,
                                    suffix=".xml")
    with open(input_file.name, "w+") as filetowrite:
        filetowrite.write(data)
    return input_file

@app.errorhandler(Exception)
def handle_generic_error(error):
    msg = "Internal error: "+str(error)
    logger.error(msg)
    rv = {'message':msg}
    return jsonify(rv)

@app.errorhandler(InvalidUsage)
def handle_invalid_usage(error):
    response = jsonify(error.to_dict())
    response.status_code = error.status_code
    return response

@app.route('/fta/validate', methods=['POST'])
def fta_validate():
    input_file = save_data_to_tmp(request.data.decode("utf-8"), "fault_tree_")         

    status, msg_err = ScramProxy.fta_validate(input_file.name)
    if status != 0:
        logger.error(f"Error validating input file. Stack trace: {msg_err}")
        raise InvalidUsage("Error validating input file", 500, {"Stack trace":msg_err})
    logger.info("Validation is successfull")
    return jsonify({"Validation":"OK"})
    
@app.route('/fta/probability', methods=['POST'])
def fta_probability():
    input_file = save_data_to_tmp(request.data.decode("utf-8"), "fault_tree_")    
    output_file = save_data_to_tmp("", "output_fta_")

    status, msg_err = ScramProxy.fta_probability(input_file.name, output_file.name)
    if status != 0:
        logger.error(f"Error when predicting probability from {input_file.name}. Stack trace: {msg_err}")
        raise InvalidUsage("Error in FTA subprocess", 500, {"Stack trace":msg_err})  
    with open(output_file.name, "r") as xml_file:
        output=xml_file.read()
    logger.info(f"{output_file.name} is read and removed")
    return Response(output, mimetype='text/xml')

@app.route('/fta/probability/new', methods=['POST'])
def fta_probability_new():
    mission_time = request.args.get("mission-time")
    limit_order = request.args.get("limit-order")
    input_file = save_data_to_tmp(request.data.decode("utf-8"), "fault_tree_")    
    output_file = save_data_to_tmp("", "output_fta_")

    status, msg_err = ScramProxy.fta_probability_new(mission_time, limit_order, input_file.name, output_file.name)
    if status != 0:
        logger.error(f"Error when predicting probability from {input_file.name}. Stack trace: {msg_err}")
        raise InvalidUsage("Error in FTA subprocess", 500, {"Stack trace":msg_err})  
    with open(output_file.name, "r") as xml_file:
        output=xml_file.read()
    logger.info(f"{output_file.name} is read and removed")
    return Response(output, mimetype='text/xml')

@app.route('/health', methods=['GET'])
def health_check(self):
    logger.info("Service health has been checked")
    return Response("{'health':'OK'}", status=200, mimetype='application/json')

def receive_signal(signalNumber, frame):
    sys.exit(0)

http_server = HTTPServer(WSGIContainer(app))
http_server.listen(args.port) 
IOLoop.instance().start()

signal.signal(signal.SIGINT, receive_signal)

