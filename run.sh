#!/bin/bash

usage() {
  echo "Use the script for building or running application"
  echo "Usage."
  echo "Build: ./run.sh build"
  echo "Running: ./run.sh start [tomcat|jetty] [port:optional]"
}

start () {
    port=18080

    if [[ -n $2 ]]
    then
        if [[ $2 =~ ^[1-9][0-9]+$ ]]
        then
            port="$2"
        else
            echo "Incorrect port number '$2'. Using default port: $port"
        fi
    fi


    case $1 in
        "jetty" )
            java -Dhttp.port=${port} -jar build/jetty-server.jar
        ;;
        "tomcat" )
            java -Dhttp.port=${port} -jar build/tomcat-server.jar
        ;;
        *)
            echo "Unknown server '$1'. Support servers is tomcat and jetty"
        ;;
    esac
}

if [[ -z $1 ]]
then
  usage
  exit 1
fi

case $1 in
    "build" )
        mvn clean package
    ;;
    "start" )
        start $2 $3
    ;;
    *)
        echo "Unknown command '$1'"
        usage
    ;;
esac



