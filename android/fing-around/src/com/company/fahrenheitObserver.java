package com.company;


public class fahrenheitObserver extends Observer{

    public fahrenheitObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("The new temperature in Fahrenheit is " + (subject.getState()*9/5 + 32));
    }
}



