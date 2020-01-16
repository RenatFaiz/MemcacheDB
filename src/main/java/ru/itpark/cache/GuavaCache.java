package ru.itpark.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import java.util.List;

public class GuavaCache {

    private static Cache<Integer, List> cache = CacheBuilder.newBuilder()
            .initialCapacity(16)
            .concurrencyLevel(4)
            .removalListener(new RemovalListener<Integer, List>() {
                @Override
                public void onRemoval(RemovalNotification<Integer, List> notification) {
                    System.out.println("Removed: " + notification.getKey() + " -> "
                            + notification.getValue() + "\nCause:" + notification.getCause());
                }
            })
            .maximumSize(20)
            .build();


    public static Cache<Integer, List> getCache() {
        return cache;
    }

    public static void showCache() {
        cache.asMap().forEach((key, value) ->
                System.out.println("In cache: " + key + " -> " + value));
    }
}
