package com.github.dizzarg.simple.http.server.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class SignInServlet extends HttpServlet {

    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException {
        String login = req.getParameter(HttpConstants.LOGIN_PARAM);
        String password = req.getParameter(HttpConstants.PASSWORD_PARAM);
        response.setContentType(HttpConstants.CONTENT_TYPE);
        if (isAuth(login, password)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private boolean isAuth(String login, String password) {
        return !(Objects.isNull(login) || Objects.isNull(password)) &&
                accountService.signin(login, password);
    }
}
