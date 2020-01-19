package ru.itpark;

import com.google.common.cache.Cache;
import ru.itpark.cache.GuavaCache;
import ru.itpark.model.Customer;
import ru.itpark.util.JdbcInMemory;
import ru.itpark.util.JdbcTemplate;
import ru.itpark.util.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        final String url = "jdbc:sqlite::memory:";
        final String pathToDb = "./customers.sqlite";
        List<Customer> customers = JdbcInMemory.executeQuery(url,
                pathToDb,
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

        System.out.println("Changed lines:" + update);

        System.out.println("List lines: " + customers.size());
        GuavaCache.getCache().put(1, customers);
        GuavaCache.getCache().put(2, customers);
        GuavaCache.showCache();



//        final String url = "jdbc:sqlite:chinook.db";
//
//        final List<Customer> customers = JdbcTemplate.executeQuery(
//                url,
//                "SELECT id, login, name, order_id FROM customers_new;",
//                new RowMapper<Customer>() {
//                    @Override
//                    public Customer map(ResultSet resultSet) throws SQLException {
//                        return new Customer(
//                                resultSet.getInt("id"),
//                                resultSet.getString("login"),
//                                resultSet.getString("name"),
//                                resultSet.getInt("order_id"));
//                    }
//                });
//        System.out.print(customers);
//        Cache cache = new Cache();
//        cache.addCustomers();
    }
}
