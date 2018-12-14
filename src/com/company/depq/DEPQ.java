package com.company.depq;

public class DEPQ<E> {
    private int initialLength = 1;
    private E[] ees;
    private int[] priority;

    public DEPQ() {
        ees = (E[]) new Object[initialLength];
        priority = new int[initialLength];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return initialLength-1;
    }

    public E getLow() {
        return ees[getLowestPriorityIndex()];
    }

    public E getHigh() {
        return ees[getHighestPriorityIndex()];
    }

    public void add(E e) {
        E[] tempList = (E[]) new Object[size() + 1];
        int[] tempPriority = new int[size() + 1];

        for (int i = 0; i < size(); i++) {
            tempList[i] = ees[i];
            tempPriority[i] = priority[i];
        }

        tempList[size()] = e;
        tempPriority[size()] = priority[getLowestPriorityIndex()] - 1;

        initialLength++;
        ees = tempList;
        priority = tempPriority;
    }

    public E removeLow() {
        int index = getLowestPriorityIndex();
        E e = ees[index];
        deleteElement(index);
        return e;
    }

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
            if (priority[i] < lowestPriority) {
                lowestPriority = priority[i];
                lowestPriorityIndex = i;
            }
        }
        return lowestPriorityIndex;
    }

    private int getHighestPriorityIndex() {
        int highestPriority = 0;
        int highestPriorityIndex = 0;

        for (int i = 0; i < size(); i++) {
            if (priority[i] > highestPriority) {
                highestPriority = priority[i];
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
                //Skip die vraag nah!
            } else if (i < index) {
                tempList[i] = ees[i];
                tempPriority[i] = priority[i];
            } else {
                tempList[i - 1] = ees[i];
                tempPriority[i - 1] = priority[i];
            }
        }

        initialLength--;
        ees = tempList;
        priority = tempPriority;
    }
}
