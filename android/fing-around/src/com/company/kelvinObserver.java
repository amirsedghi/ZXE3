package com.company;


public class kelvinObserver extends Observer{

    public kelvinObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("The new temperature in Kelvin is " + (subject.getState() + 273.15));
    }
}



