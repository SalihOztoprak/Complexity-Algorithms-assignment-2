package com.company;

import com.company.depq.DEPQMinMaxHeap;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        DEPQMinMaxHeap<Object> strings = new DEPQMinMaxHeap<>();

        System.out.println(strings.isEmpty());
        strings.add("Thomas",20);
        strings.add("Salih",200);
        strings.add("Alex",34);
        strings.add("Willem",10);
        strings.add("Thomas",12);
        strings.add("Salih",124);
        strings.add("Alex",341);
        strings.add("Willem",1345);
        strings.add("Thomas",132);
        strings.add("Salih",789);
        strings.add("Alex",567);
        strings.add("Willem",90);
        strings.add("Salih",1224);
        strings.add("Alex",3415);
        strings.add("Willem",5);
        strings.add("Thomas",13562);
        strings.add("Salih",78239);
        strings.add("Alex",56567);
        strings.add("Willem",910);
        strings.add("yess", 1);
        strings.displayHeap();
    }
}
