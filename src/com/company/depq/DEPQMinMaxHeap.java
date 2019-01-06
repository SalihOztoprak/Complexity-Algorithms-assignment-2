package com.company.depq;

public class DEPQMinMaxHeap<E> implements DEPQ {
    private int initialLength = 1;
    private E[] ees;
    private int[] priorities;

    /**
     * Constructor for creating an instance of this class
     * We will create an array for the objects and an array for the priorities
     */
    public DEPQMinMaxHeap() {
        ees = (E[]) new Object[initialLength];
        priorities = new int[initialLength];
    }

    /**
     * This method will return true if the DEPQ is empty
     *
     * @return The empty status of the list
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * This method will return the size of the DEPQ
     *
     * @return The size of the list
     */
    @Override
    public int size() {
        return initialLength - 1;
    }

    /**
     * This method will return the object with the lowest priority
     *
     * @return The element with the lowest priority
     */
    @Override
    public E getLow() {
        return ees[getLowestPriorityIndex()];
    }

    /**
     * This method will return the object with the highest priority
     *
     * @return The element with the highest priority
     */
    @Override
    public E getHigh() {
        return ees[getHighestPriorityIndex()];
    }

    /**
     * This method will add a new object with its priority to the DEPQ
     *
     * @param object   The object that will be added to the DEPQ
     * @param priority The priority that is given with the object
     */
    @Override
    public void add(Object object, int priority) {
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
        tempPriority[size()] = priority;

        //Set the old lists to the new lists
        this.ees = tempList;
        this.priorities = tempPriority;
        this.initialLength++;

        //Start the percolate so every element is at the right position
        if (size() > 1) {
            int childIndex = size() - 1;
            int height = getHeight(childIndex);
            percolateUp(childIndex, getParentIndex(childIndex), height);
        }
    }

    /**
     * This method will remove the element with the lowest priority
     *
     * @return The element with the lowest priority
     */
    @Override
    public E removeLow() {
        int index = getLowestPriorityIndex();
        E e = ees[index];
        deleteElement(index);
        return e;
    }

    /**
     * This method will remove the element with the highest priority
     *
     * @return The element with the highest priority
     */
    @Override
    public E removeHigh() {
        int index = getHighestPriorityIndex();
        E e = ees[index];
        deleteElement(index);
        return e;
    }

    /**
     * This method will percolate the selected element up in the list until it is on the correct spot
     * This method can only be accessed in this class
     *
     * @param yourIndex   The index of the object that has to percolate
     * @param parentIndex The index of the parent
     * @param height      The height of your object
     */
    private void percolateUp(int yourIndex, int parentIndex, int height) {
        boolean mustSwap = false;
        if (height % 2 == 0) {
            //EVEN (Min Level)
            if (priorities[yourIndex] > priorities[parentIndex]) {
                //Child is bigger than parent and we have to swap first
                mustSwap = true;
            }
        } else {
            //ODD (Max Level)
            if (priorities[yourIndex] < priorities[parentIndex]) {
                //Child is smaller than parent and has to swap first
                mustSwap = true;
            }
        }

        //If we have to swap elements, this statement will trigger it
        if (mustSwap) {
            swap(yourIndex, parentIndex);
            yourIndex = parentIndex;
            parentIndex = getParentIndex(yourIndex);
            height = getHeight(yourIndex);
        }

        //If we still have some height left, we can percolate again
        if (height > 1) {
            percolateUp(yourIndex, getParentIndex(parentIndex), height - 1);
        }
    }

    /**
     * This method will percolate the selected element down in the list until it is on the correct spot
     * This method can only be accessed in this class
     *
     * @param yourIndex The index of the object that has to percolate
     */
    private void percolateDown(int yourIndex) {
        int swapIndex = yourIndex;
        int currentPriority = priorities[yourIndex];

        //Create an array with all grandchildren in it
        int[] indexes = new int[4];
        indexes[0] = getLeftChildIndex(getLeftChildIndex(yourIndex));
        indexes[1] = getLeftChildIndex(getRightChildIndex(yourIndex));
        indexes[2] = getRightChildIndex(getLeftChildIndex(yourIndex));
        indexes[3] = getRightChildIndex(getRightChildIndex(yourIndex));

        if (yourIndex == 0) {
            //Check for small items
            for (int index : indexes) {
                if (index <= size()) {
                    if (currentPriority > priorities[index]) {
                        currentPriority = priorities[index];
                        swapIndex = index;
                    }
                }
            }
        } else {
            //Check for large items
            for (int index : indexes) {
                if (index <= size()) {
                    if (currentPriority < priorities[index]) {
                        currentPriority = priorities[index];
                        swapIndex = index;
                    }
                }
            }
        }
        if (swapIndex != yourIndex) {
            swap(swapIndex, yourIndex);
        }

        boolean noSwap = false;
        int compareIndex = 0;

        if (getHeight(swapIndex) % 2 == 0) {
            //EVEN ROW (children must be larger)
            if (size() > getRightChildIndex(swapIndex)) {
                if (priorities[getLeftChildIndex(swapIndex)] < priorities[getRightChildIndex(swapIndex)]) {
                    compareIndex = getLeftChildIndex(swapIndex);
                } else {
                    compareIndex = getRightChildIndex(swapIndex);
                }
            } else if (size() > getLeftChildIndex(swapIndex)) {
                compareIndex = getLeftChildIndex(swapIndex);
            } else {
                noSwap = true;
            }

            if (!noSwap)
                if (priorities[swapIndex] > priorities[compareIndex])
                    swap(swapIndex, compareIndex);

        } else {
            //ODD ROW (children must be smaller)
            if (size() > getRightChildIndex(swapIndex)) {
                if (priorities[getLeftChildIndex(swapIndex)] > priorities[getRightChildIndex(swapIndex)]) {
                    compareIndex = getLeftChildIndex(swapIndex);
                } else {
                    compareIndex = getRightChildIndex(swapIndex);
                }
            } else if (size() > getLeftChildIndex(swapIndex)) {
                compareIndex = getLeftChildIndex(swapIndex);
            } else {
                noSwap = true;
            }

            if (!noSwap)
                if (priorities[swapIndex] < priorities[compareIndex])
                    swap(swapIndex, compareIndex);
        }
    }

    /**
     * This method will swap two objects by giving two indexes
     * This method can only be accessed in this class
     *
     * @param firstIndex  The index of the first object
     * @param secondIndex The index of the second object
     */
    private void swap(int firstIndex, int secondIndex) {
        E tempE = ees[firstIndex];
        int tempIndex = priorities[firstIndex];

        ees[firstIndex] = ees[secondIndex];
        priorities[firstIndex] = priorities[secondIndex];

        ees[secondIndex] = tempE;
        priorities[secondIndex] = tempIndex;
    }

    /**
     * This method will delete an object with the given index
     * This method can only be accessed in this class
     *
     * @param index The index of the object that gets deleted
     */
    private void deleteElement(int index) {
        swap(index, size() - 1);
        initialLength--;

        E[] tempList = (E[]) new Object[size()];
        int[] tempPriority = new int[size()];

        for (int i = 0; i < size(); i++) {
            tempList[i] = ees[i];
            tempPriority[i] = priorities[i];
        }

        this.ees = tempList;
        this.priorities = tempPriority;

        if (size() > 1) {
            percolateDown(index);
        }
    }

    /**
     * This method will return the lowest priority index
     * This method can only be accessed in this class
     *
     * @return The lowest priority index
     */
    private int getLowestPriorityIndex() {
        return 0;
    }

    /**
     * This method will return the highest priority index
     * This method can only be accessed in this class
     *
     * @return The highest priority index
     */
    private int getHighestPriorityIndex() {
        if (size() > 2) {
            if (priorities[1] > priorities[2]) {
                return 1;
            }
            return 2;
        }
        return size();
    }

    /**
     * This method will return the parent of the given index
     * This method can only be accessed in this class
     *
     * @param index The index of which you want to know the parent
     * @return The parent index
     */
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    /**
     * This method will return the left child of the given index
     * This method can only be accessed in this class
     *
     * @param index The index of which you want to know the left child
     * @return The left child index
     */
    private int getLeftChildIndex(int index) {
        return (2 * index) + 1;
    }

    /**
     * This method will return the right child of the given index
     * This method can only be accessed in this class
     *
     * @param index The index of which you want to know the right child
     * @return The right child index
     */
    private int getRightChildIndex(int index) {
        return (2 * index) + 2;
    }

    /**
     * This method will return the height of the given index
     * This method can only be accessed in this class
     *
     * @param index The index of which you want to know the height
     * @return The height of the index
     */
    private int getHeight(int index) {
        return (int) Math.ceil(Math.log(index + 2) / Math.log(2)) - 1;
    }

    /**
     * This method will return a display of the heap
     * This can be used for testing purposes
     */
    public void displayHeap() {
        System.out.println("\n-----------------------------");
        System.out.println("Display of the heap:");
        System.out.println("-----------------------------");
        int height = getHeight(0);
        for (int i = 0; i < size(); i++) {
            System.out.print(priorities[i] + "   ");

            if (height != getHeight(i + 1)) {
                System.out.println();
                height = getHeight(i + 1);
            }
        }
        System.out.println("\n-----------------------------\n");
    }
}
