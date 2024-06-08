FROM ubuntu:latest
LABEL authors="dsp"

ENTRYPOINT ["top", "-b"]