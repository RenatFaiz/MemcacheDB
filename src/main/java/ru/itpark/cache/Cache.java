package ru.itpark.cache;

import ru.itpark.model.Customer;
import ru.itpark.util.Queue;

import java.util.*;


public class Cache<T> {

    final static int maxQSize = 10;

    static Queue[] queues;

    static Queue queue;

    private int index = 0;

    Cache() {
        queues = new Queue[maxQSize];
        queue = new Queue();
    }


    public T addToCache(T e) {

        if (e != null && queue.size() < 5) {
            queue.enqueue(e);
            queues[index] = queue;
            index++;
        }
        if (queue.size() > 5 && index < maxQSize) {
            queue = new Queue();
            queue.enqueue(e);
            index++;
        }
        return e;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "Queue: " + queue + "\n size: "
                + queue.size() + " index: "
                + index + "\n Array: "; // + Arrays.toString(queues);
    }

//    public String displayArray() {
//        return Arrays.toString(queues);
//    }
}
