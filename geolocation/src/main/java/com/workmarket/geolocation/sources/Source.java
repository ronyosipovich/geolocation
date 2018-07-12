package com.workmarket.geolocation.sources;

public interface Source<T> {
    boolean hasNext();
    T getNext();
}
