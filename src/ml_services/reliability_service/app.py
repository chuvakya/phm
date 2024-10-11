import json
import math
import logging
import signal
from flask import Flask, request, jsonify
    
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

@app.errorhandler(Exception)
def handle_invalid_error(error):
    rv = {'message':"Internal error: "+str(error)}
    return jsonify(rv)

@app.errorhandler(InvalidUsage)
def handle_invalid_usage(error):
    response = jsonify(error.to_dict())
    response.status_code = error.status_code
    return response

def response_json(job_id, t, l):
    p = 1 - math.e ** (-l*t)
    prob_fault = json.dumps({"id": job_id, "prob_fault": p})
    return prob_fault

def calculate_probability(dict_, i):
    if not(set(["id","time","lambda"]).issubset(dict_.keys())):
        raise InvalidUsage(f" Error in {i} dictionary. 'id', 'time', 'lambda' must be passed")
    job_id = dict_["id"]
    t = dict_["time"]
    l = dict_["lambda"]
    if not(isinstance(t,(float,int))):
        raise InvalidUsage(f" Error in {i} dictionary. Time must be integer or float")      
    if t<0:
        raise InvalidUsage(f" Error in {i} dictionary. Time < 0") 
    # lambda
    if not(isinstance(l,(float,int))):
        raise InvalidUsage(f" Error in {i} dictionary. Lambda must be integer or float")
    if l<0:
        raise InvalidUsage(f" Error in {i} dictionary. Lambda < 0")
    p = 1 - math.e ** (-l*t)  
    return {"id": job_id, "prob_fault": p}

@app.route('/prob_fault', methods=['POST'])
def request_json ():
    if not request.is_json: 
        raise InvalidUsage("Request must be json")
    try:
        dict_list = request.get_json() 
    except:
        raise InvalidUsage("Error when reading json")
    output_list = [] 

    if not isinstance(dict_list, list):
        raise InvalidUsage("Request must be list of dictionaries")

    for i in range(len(dict_list)):
        output_list.append(calculate_probability(dict_list[i], i))
    return json.dumps(output_list)

def receiveSignal(signalNumber, frame):
    print('Received:', signalNumber)
    return

if __name__=='__main__':
    signal.signal(signal.SIGINT, receiveSignal)
    logging.basicConfig()
    app.run(host = "0.0.0.0", port=5000) 
    