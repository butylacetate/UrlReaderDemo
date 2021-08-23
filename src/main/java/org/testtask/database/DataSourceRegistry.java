package org.testtask.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.testtask.utils.PropertyHandler;

public class DataSourceRegistry {

    private DataSourceRegistry() {}

    private static final PropertyHandler propertyHandler = PropertyHandler.getInstance();

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(propertyHandler.getValue("db.driver.class"));
        return DriverManager.getConnection(
                propertyHandler.getValue("db.url"),
                propertyHandler.getValue("db.user"),
                propertyHandler.getValue("db.password")
        );

    }
}
