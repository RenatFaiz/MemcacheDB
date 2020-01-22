package ru.itpark.cache;

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

    private static int index;
    private static int counter;

    final int maxSize = 5;
    List<T>[] lists = new List[maxSize];

    public List<T>[] addToArray(List<T> list) {

        if (index < maxSize) {
            lists[index] = list;
            index++;
            counter++;

        } else {
            lists = new List[maxSize *2];
            index = 0;
            lists[index] = list;
            index++;
            counter++;
        }
        return lists;
    }

    public int getIndex() {
        return index;
    }

    public static int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return "Array: " + Arrays.toString(lists) + "\nindex: "
                + index + "; counter: " + counter;
    }
}
