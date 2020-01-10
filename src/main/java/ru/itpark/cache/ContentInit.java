package ru.itpark.cache;

import ru.itpark.model.Customer;
import ru.itpark.util.JdbcTemplate;
import ru.itpark.util.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ContentInit {

    private final String url = "jdbc:sqlite:customers.sqlite";

    final List<Customer> customers = JdbcTemplate.executeQuery(
            url,
            "SELECT id, login, name, order_id FROM customers;",
            new RowMapper<Customer>() {
                @Override
                public Customer map(ResultSet resultSet) throws SQLException {
                    return new Customer(
                            resultSet.getInt("id"),
                            resultSet.getString("login"),
                            resultSet.getString("name"),
                            resultSet.getInt("order_id"));
                }
            }
    );

    int resultUpdate = JdbcTemplate.executeUpdate(url,
            "UPDATE customers SET order_id = ? WHERE name = 'Лиля';",
            0);


    final Cache<List> cache = new Cache<>();

    public List add(List list) {
        if (!list.isEmpty()) {
            cache.addToArray(list);
        }
        System.out.println("Added to cache: \n" + cache);
        return list;
    }


    public static void main(String[] args) {

        List list = new LinkedList();
        list.add(4);
        list.add(5);

        List list2 = new LinkedList();
        list2.add(8);
        list2.add(9);
        list2.add(7);

        List list3 = new LinkedList();
        list3.add(2);
        list3.add(3);
        list3.add(0);

        List list4 = new LinkedList();
        list4.add(11);
        list4.add(22);
        list4.add(10);

        final ContentInit init = new ContentInit();
//        init.add(list);
//        init.add(list2);
//        init.add(list3);
//        init.add(list4);


        init.add(init.customers);
//        init.add(init.customers);
//        System.out.println("Changed lines: " + init.resultUpdate);

    }
}
