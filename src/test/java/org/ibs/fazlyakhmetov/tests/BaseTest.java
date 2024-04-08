package org.ibs.fazlyakhmetov.tests;

import org.aeonbits.owner.ConfigFactory;
import org.ibs.fazlyakhmetov.tests.config.ConfigDB;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.*;

public class BaseTest {
    public static Statement statement;
    public static Connection connection;
    public static PreparedStatement preparedStatement;
    public static ConfigDB configDB = ConfigFactory.create(ConfigDB.class);


    @BeforeAll
    static void beforeAll() throws SQLException, ClassNotFoundException {
        Class.forName(configDB.h2Driver());
        connection = DriverManager.getConnection(configDB.jdbcUrl(),
                configDB.login(), configDB.password());
    }

    @AfterAll
    static void afterAll() throws SQLException {
        connection.close();


    }
}

