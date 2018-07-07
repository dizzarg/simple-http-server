package com.github.dizzarg.simple.http.server.auth;

import java.sql.SQLException;

public class AccountService {

    private final DBService dbService;

    public AccountService() throws SQLException {
        dbService = new DBService();
    }

    public boolean signin(String login, String password) {
        return password.equals(dbService.getPassword(login));
    }

    public void signup(String login, String password) {
        dbService.insert(login, password);
    }

}
