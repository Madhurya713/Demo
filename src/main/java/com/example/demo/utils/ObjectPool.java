package com.example.demo.utils;

public interface ObjectPool<T> {
    T borrowObject();
    void returnObject(T object);
    void invalidateObject(T object);
}
