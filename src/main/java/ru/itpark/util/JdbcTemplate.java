package ru.itpark.util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/* Класс - посредник между Java и БД.
 * Методы:
 * executeQuery работает для SELECT запросов,
 * executeUpdate - для INSERT, UPDATE, DELETE.
 */
public class JdbcTemplate {
    private JdbcTemplate() {
    }

    public static <T> List<T> executeQuery(String url, String sql, RowMapper<T> mapper) {
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            List<T> result = new LinkedList<>();

            while (resultSet.next()) {
                result.add(mapper.map(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new SqlMappingException(e);
        }
    }

    // for SELECT queries use ResultSet - rs.executeQuery();
    public static int executeUpdate(String url, String sql, int element) {
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement preparedStatement = connection.prepareStatement(sql)

        ) {
            preparedStatement.setInt(1, element);
            // preparedStatement.execute(); boolean

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlMappingException(e);
        }
    }

}
