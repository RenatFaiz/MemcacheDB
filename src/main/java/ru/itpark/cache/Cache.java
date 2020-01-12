package ru.itpark.cache;

import ru.itpark.util.Queue;

import java.util.Arrays;
import java.util.List;

/**
 * Моя реализация для кэша.
 * метод addToArray(List<T> list) - создает массив из List (пока что выбрал этот вариант)
 * метод addToCache(T e) - добавляет в очередь (FIFO) элементы типа <T>
 * метод addToArray(Queue queue) - создаёт массив из очередей Queue
 * @param <T>
 */
public class Cache<T> {

    final int maxQueue = 2;
    final int maxQSize = 2;


//    Queue queue = new Queue();
//    Queue[] queues = new Queue[maxQSize];

    List<T>[] lists = new List[maxQSize];

    private static int index;
    private static int counter;

    Cache() {
//        queues = new Queue[maxQSize];
//        queue = new Queue();
    //    index++;
    }
    public List<T>[] addToArray(List<T> list) {

        if (index < maxQSize) {
            lists[index] = list;
            index++;
            counter++;

        } else {
            lists = new List[maxQSize*2];
            index = 0;
            lists[index] = list;
            index++;
            counter++;
        }

        return lists;
    }



//        public T addToCache(T e) {
//        if (index < 10) {
//            if (e != null && queue.size() < maxQueue) {
//                queue.enqueue(e);
//
//            } else {
//                queue = new Queue();
//                queue.enqueue(e);
//                index++;
//            }
//
//        }
//        if (index > 1) {
//           // addToArray(queue);
//        }
//        return e;
//    }

//    private Queue<T>[] addToArray(Queue queue) {
//        queues[index] = queue;
//        //index++;
//        return queues;
//    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "Array: " + Arrays.toString(lists) + "\nindex: "
                + index + "; counter: " + counter;
    }

//    public String displayArray() {
//        return Arrays.toString(queues);
//    }
}
