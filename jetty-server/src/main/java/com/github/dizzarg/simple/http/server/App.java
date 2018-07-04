package com.github.dizzarg.simple.http.server;

import com.github.dizzarg.simple.http.server.servlets.AccountService;
import com.github.dizzarg.simple.http.server.servlets.SignInServlet;
import com.github.dizzarg.simple.http.server.servlets.SignUpServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;

public class App {
    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService();
        HttpServlet signInServlet = new SignInServlet(accountService);
        HttpServlet signUpServlet = new SignUpServlet(accountService);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(signInServlet), "/signin");
        context.addServlet(new ServletHolder(signUpServlet), "/signup");

        Server server = new Server(Integer.getInteger("http.port", 8080));
        server.setHandler(context);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
