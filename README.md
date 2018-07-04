#Simple HTTP servers  

The pet project. It demonstrates servlets container in embedded tomcat and jetty. 

## Requirements:

  * Oracle Java SE Development Kit 8 (or newer)
  * Apache Maven 3.x
  * Git 1.7.x (or newer)

## Build

The project is built by a command `mvn clean package`. 
And then `build` directory which contains built applications is created.  

## Start applications
### Start tomcat server

Run command `./start.sh tomcat 18081` and then you can see as follows.
```bash
% ./start.sh tomcat
Incorrect port number ''. Using default port: 18080
Jul 04, 2018 9:17:49 PM org.apache.catalina.core.StandardContext setPath
WARNING: A context path must either be an empty string or start with a '/' and do not end with a '/'. The path [/] does not meet these criteria and has been changed to []
Jul 04, 2018 9:17:49 PM org.apache.coyote.AbstractProtocol init
INFO: Initializing ProtocolHandler ["http-nio-18080"]
Jul 04, 2018 9:17:49 PM org.apache.tomcat.util.net.NioSelectorPool getSharedSelector
INFO: Using a shared selector for servlet write/read
Jul 04, 2018 9:17:49 PM org.apache.catalina.core.StandardService startInternal
INFO: Starting service Tomcat
Jul 04, 2018 9:17:49 PM org.apache.catalina.core.StandardEngine startInternal
INFO: Starting Servlet Engine: Apache Tomcat/9.0.0.M6
Jul 04, 2018 9:17:49 PM org.apache.coyote.AbstractProtocol start
INFO: Starting ProtocolHandler [http-nio-18080]
```

### Start jetty server

Run command `./start.sh jetty 18081` and then you can see as follows.

```bash
% ./start.sh jetty
Incorrect port number ''. Using default port: 18080
2018-07-04 21:17:28.363:INFO::main: Logging initialized @360ms to org.eclipse.jetty.util.log.StdErrLog
2018-07-04 21:17:28.509:INFO:oejs.Server:main: jetty-9.4.z-SNAPSHOT; built: 2018-06-05T18:24:03.829Z; git: d5fc0523cfa96bfebfbda19606cad384d772f04c; jvm 1.8.0_171-b11
2018-07-04 21:17:28.550:INFO:oejs.session:main: DefaultSessionIdManager workerName=node0
2018-07-04 21:17:28.550:INFO:oejs.session:main: No SessionScavenger set, using defaults
2018-07-04 21:17:28.553:INFO:oejs.session:main: node0 Scavenging every 600000ms
2018-07-04 21:17:28.561:INFO:oejsh.ContextHandler:main: Started o.e.j.s.ServletContextHandler@491cc5c9{/,null,AVAILABLE}
2018-07-04 21:17:28.574:INFO:oejs.AbstractConnector:main: Started ServerConnector@c81cdd1{HTTP/1.1,[http/1.1]}{0.0.0.0:18080}
2018-07-04 21:17:28.575:INFO:oejs.Server:main: Started @574ms
```

## Test application

The application knows register an account by pair login/password and authorize a pair.

Register account method listen endpoint `http://localhost:18080/signup`. 
It always returns an empty successful response. 
Example: `curl -d "login=accountLogin&password=accountPassword" "http://localhost:18080/signup"` 

Authorize account method listen endpoint `http://localhost:18080/signin`.
It returns an successful response if account registered else unauthorised response. 
Example: `curl -d "login=accountLogin&password=accountPassword" "http://localhost:18080/signip"`
 
