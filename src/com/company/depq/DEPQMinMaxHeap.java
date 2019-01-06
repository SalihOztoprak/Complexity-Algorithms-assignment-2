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

        this.ees = tempList;
        this.priorities = tempPriority;
        this.initialLength++;

        if (size() > 1) {
            int childIndex = size() - 1;
            int height = getHeight(childIndex);
            percolate(tempList, tempPriority, childIndex, getParentIndex(childIndex), height);
        }
    }

    private void percolate(E[] ees, int[] priorities, int childIndex, int parentIndex, int height) {
        boolean mustSwap = false;
        if (height % 2 == 0) {
            //EVEN (Min Level)
            if (priorities[childIndex] > priorities[parentIndex]) {
                //Child is bigger than parent and we have to swap first
                mustSwap = true;
            }
        } else {
            //ODD (Max Level)
            if (priorities[childIndex] < priorities[parentIndex]) {
                //Child is smaller than parent and has to swap first
                mustSwap = true;
            }
        }

        if (mustSwap){
            swap(ees, priorities, childIndex, parentIndex);
            childIndex = parentIndex;
            parentIndex = getParentIndex(childIndex);
            height = getHeight(childIndex);
        }

        if (height > 1) {
            percolate(this.ees, this.priorities, childIndex, getParentIndex(parentIndex), height - 1);
        }
    }

    private void swap(E[] ees, int[] priorities, int childIndex, int parentIndex) {
        E tempE = ees[childIndex];
        int tempIndex = priorities[childIndex];

        ees[childIndex] = ees[parentIndex];
        priorities[childIndex] = priorities[parentIndex];

        ees[parentIndex] = tempE;
        priorities[parentIndex] = tempIndex;
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
                tempList[i] = ees[size() - 1];
                tempPriority[i] = priorities[size() - 1];
            } else if (i < index) {
                tempList[i] = ees[i];
                tempPriority[i] = priorities[i];
            } else {
                tempList[i - 1] = ees[i];
                tempPriority[i - 1] = priorities[i];
            }
        }

        //percolate(tempList, tempPriority, index);
    }

    public void displayHeap() {
        System.out.print("Inserted elements are: ");
        for (int m = 0; m < size(); m++)
            if (priorities[m] != Integer.MAX_VALUE)
                System.out.print(priorities[m] + " ");
            else
                System.out.print("-- ");
        System.out.println();
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0; // current item
        String delimeter = "---------------------------------------------";
        System.out.println(delimeter);
        while (size() > 0) {
            if (column == 0)
                for (int k = 0; k < nBlanks; k++)
                    System.out.print(' ');
            System.out.print(priorities[j]);
            if (++j == size()) // done?
                break;
            if (++column == itemsPerRow) {
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            } else
                for (int k = 0; k < nBlanks * 2 - 2; k++)
                    System.out.print(' ');
        }
        System.out.println("\n" + delimeter);
    }

    public int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    public int getLeftChildIndex(int index) {
        return (2 * index) + 1;
    }

    public int getRightChildIndex(int index) {
        return (2 * index) + 2;
    }

    public int getHeight(int index){
        return (int) Math.ceil(Math.log(index + 2) / Math.log(2)) - 1;
    }

}
