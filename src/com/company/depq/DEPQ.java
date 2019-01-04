package com.company.depq;

public interface DEPQ<E> {
    public boolean isEmpty();

    public int size();

    public E getLow();

    public E getHigh();

    public void add(E e, int priority);

    public E removeLow();

    public E removeHigh();

}
