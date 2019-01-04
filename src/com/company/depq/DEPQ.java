package com.company.depq;

public interface DEPQ<E> {
    boolean isEmpty();

    int size();

    E getLow();

    E getHigh();

    void add(E e);

    E removeLow();

    E removeHigh();

}
