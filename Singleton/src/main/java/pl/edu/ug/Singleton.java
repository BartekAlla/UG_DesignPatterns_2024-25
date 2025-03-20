package pl.edu.ug;


import java.io.Serializable;

public class Singleton implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Singleton uniqueInstance;


    private Singleton() {
        try {
            Thread.sleep((long) (Math.random() * 20));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Singleton getInstance() {
        if (uniqueInstance == null) {

            uniqueInstance = new Singleton();
        }
        return uniqueInstance;

    }

}