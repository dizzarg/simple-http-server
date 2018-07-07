package com.github.dizzarg.simple.http.server.db;

import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBService {

    // TODO: move to configuration file
    private String url = "jdbc:h2:./h2db";
    private String name = "test";
    private String pass = "test";
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
        try (PreparedStatement statement = connection.prepareStatement("insert into users(login, password) value (?, ?)")) {
            statement.setString(1, login);
            statement.setString(2, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPassword(String login) {
        try (PreparedStatement stat = connection.prepareStatement("SELECT password from users where login=?")) {
            stat.setString(1, login);
            ResultSet resultSet = stat.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("password");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
