package com.github.dizzarg.simple.http.server;

import com.github.dizzarg.simple.http.server.servlets.AccountService;
import com.github.dizzarg.simple.http.server.servlets.SignInServlet;
import com.github.dizzarg.simple.http.server.servlets.SignUpServlet;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.http.HttpServlet;
import java.io.File;

public class App {

    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService();
        HttpServlet signInServlet = new SignInServlet(accountService);
        HttpServlet signUpServlet = new SignUpServlet(accountService);
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.getInteger("http.port", 8080));


        String tmpPath = File.createTempFile("tomcat", "simple").getAbsolutePath();
        Context ctx = tomcat.addContext("/", tmpPath);

        Tomcat.addServlet(ctx, "signin", signInServlet);
        ctx.addServletMapping("/signin", "signin");

        Tomcat.addServlet(ctx, "signup", signUpServlet);
        ctx.addServletMapping("/signup", "signup");

        tomcat.start();
        tomcat.getServer().await();
    }

}
