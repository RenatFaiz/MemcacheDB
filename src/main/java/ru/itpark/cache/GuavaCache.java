package ru.itpark.cache;

import com.google.common.cache.*;
import com.google.common.cache.Cache;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Кэш на основе библиотеки из фреймворка Google Guava
 */
public class GuavaCache {

    private static Cache<Integer, List> cache = CacheBuilder.newBuilder()
            .initialCapacity(16)
            .concurrencyLevel(4)
            .removalListener(new RemovalListener<Integer, List>() {
                @Override
                public void onRemoval(RemovalNotification<Integer, List> notification) {
                    System.out.println("Removed: " + notification.getKey()
                            + " -> " + notification.getValue()
                            + "\nCause:" + notification.getCause());
                }
            })
            .weigher(new Weigher<Integer, List>() {
                @Override
                public int weigh(Integer key, List value) {
                    int size = key + value.size();
                    System.out.println("size: " + size);
                    return size;
                }
            })
            .maximumWeight(500L)
            .recordStats()
            .build();

    public static Cache<Integer, List> getCache() {
        return cache;
    }

    public static void showCache() {
        cache.asMap().forEach((key, value) ->
                System.out.println("In cache: " + key + " -> " + value));
        System.out.println("Number of entries: " + cache.size());
    }

    public static int getBytesFromList(List list) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(list);
        out.close();
        return baos.toByteArray().length;
    }
}
