package pl.edu.ug;

public class ThreadSpecificSingleton {
    private static final ThreadLocal<ThreadSpecificSingleton> threadLocalInstance =
            ThreadLocal.withInitial(ThreadSpecificSingleton::new);

    private ThreadSpecificSingleton() {
    }

    public static ThreadSpecificSingleton getInstance() {
        return threadLocalInstance.get();
    }
}
