package com.company;

import com.company.depq.DEPQMinMaxHeap;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        DEPQMinMaxHeap<Object> strings = new DEPQMinMaxHeap<>();

        System.out.println("Is the DEPQ empty?: " + strings.isEmpty());

        strings.add("Dog",20);
        strings.add("Cat",200);
        strings.add("Fish",34);
        strings.add("Cow",10);
        strings.add("Pig",12);
        strings.add("Chicken",124);
        strings.add("Sheep",341);
        strings.add("Dolphin",1345);
        strings.add("Rabbit",132);
        strings.add("Monkey",789);
        strings.add("Elephant",567);
        strings.add("Giraffe",90);
        strings.add("Goat",1224);
        strings.add("Unicorn",5);
        strings.add("Snake",13562);
        strings.add("Human",78239);
        strings.add("Wednesday Frog",56567);
        strings.add("Owl",910);
        strings.add("T-Series", 1);

        strings.displayHeap();

        System.out.println("The lowest priority is " + strings.getLow());
        System.out.println("The highest priority is " + strings.getHigh());

        strings.removeLow();
        strings.removeHigh();

        strings.displayHeap();

        System.out.println("The lowest priority is " + strings.getLow());
        System.out.println("The highest priority is " + strings.getHigh());

        System.out.println("The size of the DEPQ is " + strings.size());
    }
}
