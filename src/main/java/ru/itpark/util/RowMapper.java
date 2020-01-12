package ru.itpark.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Интерфейс для создания соответствий между
 * результатами запроса SQL и объекта ResultSet
 */
@FunctionalInterface
public interface RowMapper<T> {
    T map(ResultSet resultSet) throws SQLException;
}
