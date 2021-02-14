#!/bin/bash
cd `dirname $0`
BIN_DIR=`pwd`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf

SERVER_NAME="fundf10"

PID=$(ps -ef | grep ${CONF_DIR} | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
    echo ${SERVER_NAME} is already stopped!
else
    echo "PID: ${PID}"
    kill -9 ${PID}
    echo ${SERVER_NAME} stopped successfully!
fi
