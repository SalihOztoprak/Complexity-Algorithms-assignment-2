package com.company.depq;

public interface DEPQ<E> {
    /**
     * This method will return true if the DEPQ is empty
     *
     * @return The empty status of the list
     */
    boolean isEmpty();

    /**
     * This method will return the size of the DEPQ
     *
     * @return The size of the list
     */
    int size();

    /**
     * This method will return the object with the lowest priority
     *
     * @return The element with the lowest priority
     */
    E getLow();

    /**
     * This method will return the object with the highest priority
     *
     * @return The element with the highest priority
     */
    E getHigh();


    /**
     * This method will add a new object with its priority to the DEPQ
     *
     * @param e   The object that will be added to the DEPQ
     * @param priority The priority that is given with the object
     */
    void add(E e, int priority);

    /**
     * This method will remove the element with the lowest priority
     *
     * @return The element with the lowest priority
     */
    E removeLow();

    /**
     * This method will remove the element with the highest priority
     *
     * @return The element with the highest priority
     */
    E removeHigh();

}
