    
import subprocess
from typing import Tuple

PIPE = -1

class ScramProxy():   
    @staticmethod
    def fta_validate(input_file:str)-> Tuple[int, str]:
        output = subprocess.run(["scram","--validate", input_file], stderr=PIPE)
        return output.returncode, output.stderr.decode("utf-8")
    
    @staticmethod
    def fta_probability(input_file:str, output_file:str)-> Tuple[int, str]:
        output = subprocess.run(["scram","--bdd","--probability","true", "-o", output_file, input_file], stderr=PIPE)
        return output.returncode, output.stderr.decode("utf-8")

    @staticmethod
    def fta_probability_new(mission_time:str, limit_order:str, input_file:str, output_file:str)-> Tuple[int, str]:
        output = subprocess.run(["scram","--bdd","--probability","true", "--importance", "true", "--mission-time", mission_time, "--limit-order", limit_order, "-o", output_file, input_file], stderr=PIPE)
        return output.returncode, output.stderr.decode("utf-8")

