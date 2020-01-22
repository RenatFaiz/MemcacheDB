package ru.itpark.util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс для взаимодействия Java и баз данных.
 */
public class JdbcTemplate {
    private JdbcTemplate() {
    }

    /**
     * Метод executeQuery работает для SELECT запросов.
     *
     * @param url    адрес базы данных
     * @param sql    SQL запрос
     * @param mapper объект класса RowMapper для сопоставления строк таблиц БД
     *               с полями нужного класса
     * @param <T>    обобщенный параметр
     * @return возвращает LinkedList из результатов запроса
     */
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

    /**
     * Метод executeUpdate для запросов INSERT, UPDATE, DELETE.
     *
     * @param url     адрес базы данных
     * @param sql     SQL запрос
     * @param element значение подставляемого в запрос элемента (int)
     * @return возвращает число изменённых строк
     */
    public static int executeUpdate(String url, String sql, int element) {
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, element);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SqlMappingException(e);
        }
    }
}
