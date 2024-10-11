# Python support can be specified down to the minor or micro version
# (e.g. 3.6 or 3.6.3).
# OS Support also exists for jessie & stretch (slim and full).
# See https://hub.docker.com/r/library/python/ for all supported Python
# tags from Docker Hub.
FROM python:3.6-alpine
LABEL maintainer="tanyavgm@gmail.com"

# If you prefer miniconda:
#FROM continuumio/miniconda3

LABEL Name=reliability_service Version=0.0.1
EXPOSE 5000

WORKDIR /app
ADD . /app

# Using pip:
RUN python3 -m pip install -r requirements.txt
ENTRYPOINT ["python", "init_tornado.py"]

