package ru.itpark.cache;

import ru.itpark.model.Customer;
import ru.itpark.util.Queue;

import java.util.*;


public class Cache<T> {

    final  int maxQSize = 10;

    public  Queue[] queues = new Queue[maxQSize];

    Queue queue = new Queue();

    private int index = 0;


    public T addToCache(T e) {

        if (e != null && queue.size() < 10) {
            queue.enqueue(e);
            //queues[index] = queue;
            index++;
        } else {
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
        return "NewCache: " + queue + "\n size: "
                + queue.size() + " index: "
                + index + "\n Array: "; // + Arrays.toString(queues);
    }

//    public String displayArray() {
//        return Arrays.toString(queues);
//    }
}
