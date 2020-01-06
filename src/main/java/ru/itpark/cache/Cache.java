package ru.itpark.cache;

import ru.itpark.model.Customer;
import ru.itpark.util.Queue;

import java.util.LinkedList;
import java.util.List;


public class Cache<T> {

    Queue queue = new Queue();

    public void addToCache(T e) {
        if (e != null && queue.size() < 10) {
            queue.enqueue(e);
        }
    }

    @Override
    public String toString() {
        return "NewCache: "
                + queue + "\n" + queue.size();
    }
}
