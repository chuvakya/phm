# Python support can be specified down to the minor or micro version
# (e.g. 3.6 or 3.6.3).
# See https://hub.docker.com/r/library/python/ for all supported Python
# tags from Docker Hub.

FROM ubuntu:18.04
LABEL maintainer="tanyavgm@gmail.com"

LABEL Name=fault_tree_analisys_service Version=0.0.2
EXPOSE 5001

WORKDIR /app
ADD . /app


ENV RUNTIME_PACKAGES \
    apt-utils libxml2 libboost-filesystem1.65.1 libboost-program-options1.65.1 \
    libtcmalloc-minimal4
    
RUN apt-get update && \
    apt-get install -y --no-install-recommends $RUNTIME_PACKAGES && \
    rm -rf /var/lib/apt/lists/*

RUN apt-get update
RUN apt-get install -y software-properties-common
RUN apt install software-properties-common

RUN add-apt-repository ppa:rakhimov/scram
RUN apt-get update
RUN apt-get install scram

RUN add-apt-repository ppa:deadsnakes/ppa
RUN apt-get update
RUN apt-get install python3.6 
RUN apt-get install -y python3-pip
RUN apt-get install -y python3-setuptools

RUN python3 -m pip install -r requirements.txt 

ENTRYPOINT ["python3", "service.py", "-p" , "5003"]