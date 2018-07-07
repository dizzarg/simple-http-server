# Simple HTTP servers  

The pet project. It demonstrates servlets container in embedded tomcat and jetty. 

## Requirements:

  * Oracle Java SE Development Kit 8 (or newer)
  * Apache Maven 3.x
  * Git 1.7.x (or newer)

## Run:

* rebuild and run unit tests 
```
% ./run.sh build 
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO] 
[INFO] simple-http-server
[INFO] core
[INFO] auth
[INFO] web
[INFO] tomcat-server
[INFO] jetty-server
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building simple-http-server 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO] 
[INFO] simple-http-server ................................. SUCCESS [  0.188 s]
[INFO] core ............................................... SUCCESS [  0.002 s]
[INFO] db ................................................. SUCCESS [  1.857 s]
[INFO] auth ............................................... SUCCESS [  0.099 s]
[INFO] web ................................................ SUCCESS [  2.001 s]
[INFO] tomcat-server ...................................... SUCCESS [  2.430 s]
[INFO] jetty-server ....................................... SUCCESS [  2.066 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 8.816 s
[INFO] Finished at: 2018-07-07T22:43:58+03:00
[INFO] Final Memory: 36M/247M
[INFO] ------------------------------------------------------------------------
```

And then `build` directory which contains built applications is created.  

* Run server:
    * Run tomcat:  
```
% /run.sh start tomcat
Jul 06, 2018 9:57:06 AM org.apache.catalina.core.StandardContext setPath
WARNING: A context path must either be an empty string or start with a '/' and do not end with a '/'. The path [/] does not meet these criteria and has been changed to []
Jul 06, 2018 9:57:07 AM org.apache.coyote.AbstractProtocol init
INFO: Initializing ProtocolHandler ["http-nio-18080"]
Jul 06, 2018 9:57:07 AM org.apache.tomcat.util.net.NioSelectorPool getSharedSelector
INFO: Using a shared selector for servlet write/read
Jul 06, 2018 9:57:07 AM org.apache.catalina.core.StandardService startInternal
INFO: Starting service Tomcat
Jul 06, 2018 9:57:07 AM org.apache.catalina.core.StandardEngine startInternal
INFO: Starting Servlet Engine: Apache Tomcat/9.0.0.M6
Jul 06, 2018 9:57:07 AM org.apache.coyote.AbstractProtocol start
INFO: Starting ProtocolHandler [http-nio-18080]
```

    * Run jetty:  
```
% /run.sh start jetty
018-07-06 09:58:24.098:INFO::main: Logging initialized @310ms to org.eclipse.jetty.util.log.StdErrLog
2018-07-06 09:58:24.212:INFO:oejs.Server:main: jetty-9.4.z-SNAPSHOT; built: 2018-06-05T18:24:03.829Z; git: d5fc0523cfa96bfebfbda19606cad384d772f04c; jvm 1.8.0_121-b13
2018-07-06 09:58:24.248:INFO:oejs.session:main: DefaultSessionIdManager workerName=node0
2018-07-06 09:58:24.248:INFO:oejs.session:main: No SessionScavenger set, using defaults
2018-07-06 09:58:24.251:INFO:oejs.session:main: node0 Scavenging every 600000ms
2018-07-06 09:58:24.257:INFO:oejsh.ContextHandler:main: Started o.e.j.s.ServletContextHandler@e720b71{/,null,AVAILABLE}
2018-07-06 09:58:24.268:INFO:oejs.AbstractConnector:main: Started ServerConnector@c81cdd1{HTTP/1.1,[http/1.1]}{0.0.0.0:18080}
2018-07-06 09:58:24.268:INFO:oejs.Server:main: Started @482ms
```

* Stop:

The program is terminated response to a user interrupt, such as typing ^C (Ctrl + C), or a system-wide event of a shutdown.


## Test application

The application knows register an account by pair login/password and authorize a pair.

Register account method listen endpoint `http://localhost:18080/signup`. 
It always returns an empty successful response. 
Example: `curl -d "login=accountLogin&password=accountPassword" "http://localhost:18080/signup"` 

Authorize account method listen endpoint `http://localhost:18080/signin`.
It returns an successful response if account registered else unauthorised response. 
Example: `curl -d "login=accountLogin&password=accountPassword" "http://localhost:18080/signip"`
 
