package com.company.depq;

public interface MinMaxHeap<E> {
    public boolean isEmpty();

    public int size();

    public E getLow();

    public E getHigh();

    public void add(E e);

    public E removeLow();

    public E removeHigh();

}
