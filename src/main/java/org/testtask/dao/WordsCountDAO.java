package org.testtask.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.testtask.database.DataSourceRegistry;

public class WordsCountDAO {
    private static final Logger logger = Logger.getLogger(WordsCountDAO.class);

    private static final String UPSERT_WORD_COUNT = "insert into words_count VALUES ('%s', %s) ON CONFLICT (word) do update SET count = %s;";

    public void upsert(Map<String, Integer> wordsCount) {
        logger.info("Update stats in DB");
        String sql = wordsCount.entrySet().stream().map(entry ->
                String.format(UPSERT_WORD_COUNT, entry.getKey(), entry.getValue(), entry.getValue())).collect(Collectors.joining("\n"));

        try (Connection conn = DataSourceRegistry.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.execute();
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.error(e);
        }
    }
}
