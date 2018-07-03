package com.github.dizzarg.simple.http.server;

import com.github.dizzarg.simple.http.server.servlets.AllRequestsServlet;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.http.HttpServlet;
import java.io.File;

public class App {

    public static void main(String[] args) throws Exception {
        HttpServlet allRequestsServlet = new AllRequestsServlet();
        tomcatServer(allRequestsServlet);
    }

    private static void tomcatServer(HttpServlet allRequestsServlet) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.getInteger("http.port", 8080));

        Context ctx = tomcat.addContext("/", new File(".").getAbsolutePath());

        Tomcat.addServlet(ctx, "allRequests", allRequestsServlet);
        ctx.addServletMapping("/*", "allRequests");

        tomcat.start();
        tomcat.getServer().await();
    }
}
