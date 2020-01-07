package ru.itpark.cache;

import ru.itpark.model.Customer;
import ru.itpark.util.JdbcTemplate;
import ru.itpark.util.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ContentInit {

    private final String url = "jdbc:sqlite:chinook.db";

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






//    public List<T> addToCache() {
//        List<T> cache = null;
//        while (customers.size() < 1000) {
//            cache.add(customers);
//        }
//    }

    public void addCustomers() {
//        HashSet<List<Customer>> set = new LinkedHashSet<>(10);
//        set.add(customers);
//        System.out.println(customers);
//
//        List<List<Customer>> list = new LinkedList<>();
//        list.add(customers);
//        System.out.println(list);
//
//
////        List list = new ArrayList();
////        list.add(customers);
//
//        Set<List<Customer>> s = Collections.synchronizedSet(set);
//        System.out.println(s);
//        Queue<List<Customer>> queue = new LinkedBlockingQueue<>(15);



//        final NewCache<List> cache = new NewCache<>();
//        cache.addToCache(customers);
//        cache.addToCache(customers);
//        cache.addToCache(customers);
//        System.out.println(cache);
    }
    //static Cache<List> cache;
    final Cache<List> cache = new Cache<>();

    public List add(List list) {
        cache.addToCache(list);
        System.out.println(cache);
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

        final ContentInit init = new ContentInit();
        init.add(list);
        init.add(list2);
        init.add(list3);


        //final Cache<List> cache = new Cache<>();

      //  cache.add(cache.customers);

    }


}
