package com.company;

/**
 * Created by evolerup on 3/17/16.
 */
public class Singleton {
    private static Singleton instance;

    public Singleton()
    {
        System.out.println("Singleton(): Initializing Instance");
    }

    public static Singleton getInstance()
    {
        if (instance == null)
        {
            synchronized(Singleton.class)
            {
                if (instance == null)
                {
                    System.out.println("getInstance(): First time getInstance was invoked!");
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }

    public void doSomething()
    {
        System.out.println("doSomething(): Singleton does something!");
    }
}
