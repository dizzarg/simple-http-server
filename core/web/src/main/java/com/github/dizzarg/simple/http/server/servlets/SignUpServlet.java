package com.github.dizzarg.simple.http.server.servlets;

import com.github.dizzarg.simple.http.server.auth.AccountService;

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
        String login = req.getParameter(HttpConstants.LOGIN_PARAM);
        String password = req.getParameter(HttpConstants.PASSWORD_PARAM);
        response.setContentType(HttpConstants.CONTENT_TYPE);
        accountService.signup(login, password);
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
