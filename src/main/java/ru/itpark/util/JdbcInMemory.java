package ru.itpark.util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JdbcInMemory {
    private JdbcInMemory() {
    }

    public static <T> List<T> executeQuery(String url, String pathToDb,
                                           String sql, RowMapper<T> mapper) {
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
        ) {
            statement.execute("restore from " + pathToDb);
            ResultSet resultSet = statement.executeQuery(sql);

            List<T> results = new LinkedList<>();

            while(resultSet.next()) {
                results.add(mapper.map(resultSet));
            }
            resultSet.close();
            return results;

        } catch (SQLException e) {
            throw  new SqlMappingException(e);
        }

    }
}
