package ru.itpark;

import ru.itpark.model.Customer;
import ru.itpark.util.JdbcTemplate;
import ru.itpark.util.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static ru.itpark.cache.GuavaCache.getCache;
import static ru.itpark.cache.GuavaCache.showCache;

public class Main {

    /*
     * В методе main тестируется добавление и отображение кэша с помощью класса GuavaCache.
     * Файл базы данных для теста customers.sqlite
     */
    private final static String url = "jdbc:sqlite:customers.sqlite";

    private static List<Customer> customers = JdbcTemplate.executeQuery(url,
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

    private static int elementToUpdate = JdbcTemplate.executeUpdate(url,
            "UPDATE customers SET order_id = ? WHERE name = 'Лиля';",
            7);

    public static void main(String[] args) {


        Thread thread1 = new Thread(() -> putValue(1, customers));
        thread1.start();

        Thread thread2 = new Thread(() -> putValue(2, customers));
        thread2.start();

        Thread thread3 = new Thread(() -> putValue(3, customers));
        thread3.start();

        Thread thread4 = new Thread(() -> updateElement(elementToUpdate));
        thread4.start();

        Thread thread5 = new Thread(() -> updateElement(elementToUpdate));
        thread5.start();


    }

    private static void putValue(int key, List list) {
        getCache().put(key, list);
        showCache();
    }

    private static void updateElement(int element) {
        System.out.println("Changed lines: " + element);
    }
}
