package ru.itpark.util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


/* Класс - посредник между джавой и БД.
 * Методы:
 * executeQuery работает для SELECT запросов,
 * executeUpdate - для INSERT, UPDATE, DELETE. Перед началом работы с БД,
 * она скачивается (restore from) в указанный URL
 */
public class JdbcInMemory {
    private JdbcInMemory() {
    }

    public static <T> List<T> executeQuery(String url, String pathToDb,
                                           String sql, RowMapper<T> mapper) {
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()
        ) {
            statement.execute("restore from " + pathToDb);
            ResultSet resultSet = statement.executeQuery(sql);

            List<T> results = new LinkedList<>();

            while (resultSet.next()) {
                results.add(mapper.map(resultSet));
            }
            resultSet.close();
            return results;

        } catch (SQLException e) {
            throw new SqlMappingException(e);
        }

    }

    public static int executeUpdate(String url, String pathToDb,
                                    String sql, int element) {
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();

        ) {
            statement.execute("restore from " + pathToDb);

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, element);
            int result = preparedStatement.executeUpdate();

            preparedStatement.close();
            return result;
        } catch (SQLException e) {
            throw new SqlMappingException(e);
        }
    }
}
