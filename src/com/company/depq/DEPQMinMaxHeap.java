package com.company.depq;

public class DEPQMinMaxHeap<E> implements DEPQ {
    private int initialLength = 1;
    private E[] ees;
    private int[] priorities;

    public DEPQMinMaxHeap() {
        ees = (E[]) new Object[initialLength];
        priorities = new int[initialLength];
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return initialLength - 1;
    }

    @Override
    public E getLow() {
        return ees[getLowestPriorityIndex()];
    }

    @Override
    public E getHigh() {
        return ees[getHighestPriorityIndex()];
    }

    @Override
    public void add(Object object, int prio) {
        //Create new array's
        E[] tempList = (E[]) new Object[size() + 1];
        int[] tempPriority = new int[size() + 1];

        //Add every current element to the new array
        for (int i = 0; i < size(); i++) {
            tempList[i] = ees[i];
            tempPriority[i] = priorities[i];
        }

        //add the new element to the array
        tempList[size()] = (E) object;
        tempPriority[size()] = prio;

        perculate(tempList, tempPriority, size());
    }

    private void perculate(E[] ees, int[] priorities, int affectedIndex) {
//        int height = (int) Math.ceil(Math.log(size() + 1) / Math.log(2)) - 1;
//        int parentIndex = size() / 2;
//
//        if ((height % 2) == 0) {
//            //Child has to be lower than parent
//            if (tempPriority[size()] > tempPriority[parentIndex]) {
//
//            }
//        } else {
//            //Child has to be higher than parent
//            if (tempPriority[size()] < tempPriority[parentIndex]) {
//
//            }
//        }
//
//        initialLength = ees.length;
//        this.ees = ees;
//        this.priorities = priorities;
    }

    @Override
    public E removeLow() {
        int index = getLowestPriorityIndex();
        E e = ees[index];
        deleteElement(index);
        return e;
    }

    @Override
    public E removeHigh() {
        int index = getHighestPriorityIndex();
        E e = ees[index];
        deleteElement(index);
        return e;
    }

    private int getLowestPriorityIndex() {
        int lowestPriority = 0;
        int lowestPriorityIndex = 0;

        for (int i = 0; i < size(); i++) {
            if (priorities[i] < lowestPriority) {
                lowestPriority = priorities[i];
                lowestPriorityIndex = i;
            }
        }
        return lowestPriorityIndex;
    }

    private int getHighestPriorityIndex() {
        int highestPriority = 0;
        int highestPriorityIndex = 0;

        for (int i = 0; i < size(); i++) {
            if (priorities[i] > highestPriority) {
                highestPriority = priorities[i];
                highestPriorityIndex = i;
            }
        }
        return highestPriorityIndex;
    }

    private void deleteElement(int index) {
        E[] tempList = (E[]) new Object[size()];
        int[] tempPriority = new int[size()];

        for (int i = 0; i < size(); i++) {
            if (i == index) {
                tempList[i] = ees[size()-1];
                tempPriority[i] = priorities[size()-1];
            } else if (i < index) {
                tempList[i] = ees[i];
                tempPriority[i] = priorities[i];
            } else {
                tempList[i - 1] = ees[i];
                tempPriority[i - 1] = priorities[i];
            }
        }

        perculate(tempList,tempPriority, index);
    }
}
