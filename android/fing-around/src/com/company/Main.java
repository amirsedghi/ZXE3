package com.company;

public class Main {

    public static void main(String[] args) {

        Subject subject = new Subject();

        new fahrenheitObserver(subject);
        new kelvinObserver(subject);


        System.out.println("\nCurrent temperature in Celsius: 25");
        subject.setState(25);
        System.out.println("\nCurrent temperature in Celsius: 40");
        subject.setState(40);

    }
}


