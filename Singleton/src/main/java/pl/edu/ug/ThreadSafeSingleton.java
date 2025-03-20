package pl.edu.ug;

public class ThreadSafeSingleton {

    private ThreadSafeSingleton() {
        try {
            Thread.sleep((long) (Math.random() * 20));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static class Holder {
        private static final ThreadSafeSingleton INSTANCE = new ThreadSafeSingleton();
    }

    public static synchronized ThreadSafeSingleton getInstance() {
        return Holder.INSTANCE;
    }
}

