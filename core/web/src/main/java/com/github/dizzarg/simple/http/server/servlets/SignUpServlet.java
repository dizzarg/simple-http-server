package com.github.dizzarg.simple.http.server.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpServlet extends HttpServlet {

    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        response.setContentType("text/html;charset=utf-8");
        accountService.signup(login, password);
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
