package ru.itpark.cache;

import java.util.Arrays;
import java.util.List;

/**
 * Реализация кэша в виде массивов из List.
 *
 * @param <T>
 */
public class Cache<T> {

    private static int index;
    private static int counter;

    final int maxSize = 5;
    List<T>[] lists = new List[maxSize];

    /**
     * Метод addToArray(List<T> list) - создает массив из List.
     * Каждый новый лист добавляется в конец массива.
     * {@code index} - номер листа внутри массива.
     * {@code counter} - счетчик всех листов в кэше.
     *
     * @param list принимает объекты типа List
     * @return возвращает массив из List
     */
    public List<T>[] addToArray(List<T> list) {

        if (index < maxSize) {
            lists[index] = list;
            index++;
            counter++;

        } else {
            lists = new List[maxSize * 2];
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
