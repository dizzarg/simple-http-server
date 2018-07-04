package com.github.dizzarg.simple.http.server.servlets;

import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBService {

    String url = "jdbc:h2:./h2db";
    String name = "test";
    String pass = "test";
    private Connection connection;

    public DBService() throws SQLException {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL(url);
        ds.setUser(name);
        ds.setPassword(pass);

        connection = ds.getConnection();
        connection.setAutoCommit(true);
        connection
                .createStatement()
                .execute("create table if not exists users (id bigint auto_increment, login varchar(256), password varchar(256), primary key (id));");
    }

    public void insert(String login, String password) {
        try {
            connection.createStatement().execute("insert into users(login, password) " +
                    "values ('" + login + "', '" + password + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPassword(String login) {
        try {
            ResultSet resultSet = connection.createStatement()
                    .executeQuery("SELECT password from users where" + " login='" + login + "'");
            if (resultSet.next()) {
                return resultSet.getString("password");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
