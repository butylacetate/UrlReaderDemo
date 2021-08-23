package org.testtask.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class InitialDB {
    private static final Logger logger = Logger.getLogger(InitialDB.class);

    private static final String CREATE_TABLE_WORDS_COUNT =
            "CREATE TABLE IF NOT EXISTS words_count " +
                    "( " +
                    "    word varchar primary key, " +
                    "    count numeric not null  " +
                    ");";

    public void init() {
        logger.info("Init DB");

        try (Connection conn = DataSourceRegistry.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                statement.execute(CREATE_TABLE_WORDS_COUNT);
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e);
            System.exit(1);
        }
    }
}
