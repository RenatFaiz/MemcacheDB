package ru.itpark;

import ru.itpark.model.Customer;
import ru.itpark.util.JdbcTemplate;
import ru.itpark.util.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        final String url = "jdbc:sqlite:chinook.db";

        final List<Customer> customers = JdbcTemplate.executeQuery(
                url,
                "SELECT id, login, name, order_id FROM customers_new;",
                new RowMapper<Customer>() {
                    @Override
                    public Customer map(ResultSet resultSet) throws SQLException {
                        return new Customer(
                                resultSet.getInt("id"),
                                resultSet.getString("login"),
                                resultSet.getString("name"),
                                resultSet.getInt("order_id"));
                    }
                });
        System.out.print(customers);
//        Cache cache = new Cache();
//        cache.addCustomers();
    }
}
