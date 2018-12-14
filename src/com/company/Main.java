package com.company;

import com.company.depq.DEPQ;

public class Main {

    public static void main(String[] args) {
	new Main().run();
    }

    private void run() {
        DEPQ<String> strings = new DEPQ<>();

        System.out.println(strings.isEmpty());
        strings.add("Geld"); //Hoogste prioriteit
        strings.add("Gelukkig"); //2 na hoogste prioriteit
        strings.add("Liefde"); //3 na hoogste prioriteit
        strings.add("Dood"); //Laagste prioriteit
        System.out.println(strings.size());
        System.out.println(strings.getHigh());
        System.out.println(strings.getLow());
        System.out.println(strings.isEmpty());
        strings.add("We gaan naar huis");
        strings.removeHigh();
        strings.removeHigh();
        strings.removeHigh();
        strings.removeHigh();
        System.out.println(strings.getHigh());
    }
}
