package com.github.dizzarg.simple.http.server;

import com.github.dizzarg.simple.http.server.servlets.AllRequestsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;

public class App {
    public static void main(String[] args) throws Exception {
        HttpServlet allRequestsServlet = new AllRequestsServlet();
        jettyServer(allRequestsServlet);
    }

    private static void jettyServer(HttpServlet allRequestsServlet) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestsServlet), "/*");

        Server server = new Server(Integer.getInteger("http.port", 8080));
        server.setHandler(context);

        server.start();
        server.join();
    }
}
