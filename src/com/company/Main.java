package com.company;

import com.company.depq.DEPQMinMaxHeap;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
//        DEPQMinMaxHeap<Object> strings = new DEPQMinMaxHeap<>();
//
//        System.out.println(strings.isEmpty());
//        strings.add("Thomas",20);
//        strings.add("Salih",200);
//        strings.add("Alex",34);
//        strings.add("Willem",10);
//        System.out.println(strings.size());
//        System.out.println(strings.getHigh());
//        System.out.println(strings.getLow());
//        System.out.println(strings.isEmpty());
//        strings.add("Frank",60);
//        strings.removeHigh();
//        strings.removeLow();
//        System.out.println(strings.getHigh());

        int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int index = 10;

        int[] tempList = new int[10];

        for (int i = 0; i < numbers.length; i++) {
            if (i == index && index != tempList.length) {
                tempList[index] = numbers[index - 1];
            } else {
                tempList[i] = numbers[i];
            }

        }

        System.out.println(Arrays.toString(tempList));
    }
}
