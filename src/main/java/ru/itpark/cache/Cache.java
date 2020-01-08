package ru.itpark.cache;

import ru.itpark.util.Queue;

import java.util.Arrays;


public class Cache<T> {

    final int maxQueue = 2;
    final int maxQSize = 10;


    Queue queue = new Queue();
    Queue[] queues = new Queue[maxQSize];

    private static int index = 0;

//    Cache() {
//        queues = new Queue[maxQSize];
//        queue = new Queue();
//    }


    public T addToCache(T e) {
        if (index < 10) {
            if (e != null && queue.size() < maxQueue) {
                queue.enqueue(e);
                index = 1;
            } else {
                queue = new Queue();
                queue.enqueue(e);
                index++;
            }
        }
        if (index > 10) {
            addToArray(queue);
        }
        return e;
    }

    private Queue<T>[] addToArray(Queue queue) {
        queues[index] = queue;
        //index++;
        return queues;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "Queue: " + queue + "\nsize: "
                + queue.size() + " index: "
                + index + "\nArray: " + Arrays.toString(queues);
    }

//    public String displayArray() {
//        return Arrays.toString(queues);
//    }
}
