package com.company.depq;

public interface DEPQ<E> {
    boolean isEmpty();

    int size();

    E getLow();

    E getHigh();

    void add(E e, int priority);

    E removeLow();

    E removeHigh();

}
