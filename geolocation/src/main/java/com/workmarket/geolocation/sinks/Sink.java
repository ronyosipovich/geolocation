package com.workmarket.geolocation.sinks;

public interface Sink<T> {
    void writeOne(T item, boolean hasNext);
    void close();
    void open();
}
