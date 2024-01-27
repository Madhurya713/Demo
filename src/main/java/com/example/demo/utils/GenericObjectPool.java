package com.example.demo.utils;

import java.util.LinkedList;
import java.util.function.Supplier;

public class GenericObjectPool<T> implements ObjectPool<T> {
    private final LinkedList<T> availableObjects = new LinkedList<>();
    private final Supplier<T> objectCreator;
    private final int maxSize;

    public GenericObjectPool(Supplier<T> objectCreator, int maxSize) {
        this.objectCreator = objectCreator;
        this.maxSize = maxSize;
    }

    @Override
    public synchronized T borrowObject() {
        if (availableObjects.isEmpty()) {
            return objectCreator.get();
        } else {
            return availableObjects.removeFirst();
        }
    }

    @Override
    public synchronized void returnObject(T object) {
        if (object != null && availableObjects.size() < maxSize) {
            availableObjects.addFirst(object);
        }
    }

    @Override
    public synchronized void invalidateObject(T object) {
        // Handle the invalidation of an object (optional)
    }
}
