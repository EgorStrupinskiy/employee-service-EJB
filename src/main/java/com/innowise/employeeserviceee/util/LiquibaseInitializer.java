package com.innowise.employeeserviceee.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class LiquibaseInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String url = "jdbc:mysql://localhost:3306/my_db?useSSL=false";
        String username = "bestuser";
        String password = "bestuser";
        String changelog = "db/changelog/db.changelog-master.yaml";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Liquibase liquibase = new Liquibase(changelog, new ClassLoaderResourceAccessor(), new JdbcConnection(connection));
            liquibase.update("");
        } catch (SQLException | LiquibaseException e) {
            e.printStackTrace();
        }
    }
}
