#!/bin/bash

if [[ -z $1 ]]
then
  echo "Empty params"
  echo "usage: ./script.sh [tomcat|jetty] [port]"
fi


port=18080

if [[ $2 =~ '^[0-9]+$' ]]
then
    port="$2"
else
    echo "Incorrect port number '$2'. Using default port: $port"
fi


case $1 in
    "jetty" )
        java -Dhttp.port=$port -jar jetty-server/simple-jetty-http-server.jar
    ;;
    "tomcat" )
        java -Dhttp.port=$port -jar tomcat-server/simple-tomcat-http-server.jar
    ;;
    *)
        echo "Unknown server '$1'. Support servers is tomcat and jetty"
    ;;
esac
