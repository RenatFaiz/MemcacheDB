package ru.itpark;

import com.google.common.cache.Cache;
import ru.itpark.cache.GuavaCache;
import ru.itpark.model.Customer;
import ru.itpark.util.JdbcTemplate;
import ru.itpark.util.RowMapper;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ru.itpark.cache.GuavaCache.getCache;
import static ru.itpark.cache.GuavaCache.showCache;

public class Main {


    public static void main(String[] args) throws InterruptedException {

//        final String url = "jdbc:sqlite::memory:";
//        final String pathToDb = "./customers.sqlite";
        final String url = "jdbc:sqlite:customers.sqlite";

        List<Customer> customers = JdbcTemplate.executeQuery(url,
                "SELECT * FROM customers",
                new RowMapper<Customer>() {
                    @Override
                    public Customer map(ResultSet resultSet) throws SQLException {
                        return new Customer(
                                resultSet.getInt("id"),
                                resultSet.getString("login"),
                                resultSet.getString("name"),
                                resultSet.getInt("order_id")
                        );
                    }
                });
        int update = JdbcTemplate.executeUpdate("jdbc:sqlite:customers.sqlite",
                "UPDATE customers SET order_id = ? WHERE name = 'Лиля';",
                7);

        System.out.println("Changed lines: " + update);

//        System.out.println("List lines: " + customers.size());
        getCache().put(1, customers);
        getCache().put(2, customers);
        showCache();

    }
}
