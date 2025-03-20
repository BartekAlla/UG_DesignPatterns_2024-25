package pl.edu.ug;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;


import static org.junit.jupiter.api.Assertions.*;

class ThreadSafeSingletonTest {
    private static final int THREAD_COUNT = 4;

    @Test
    void testSingletonConcurrency() throws InterruptedException {
        Set<Integer> hashCodes = ConcurrentHashMap.newKeySet();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(() -> {
                Singleton instance = Singleton.getInstance();
                hashCodes.add(System.identityHashCode(instance));
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Unique instances for Classic Singleton: " + hashCodes.size());
        assertTrue(hashCodes.size() > 1);
    }

    @Test
    void testThreadSafeSingletonConcurrency() throws InterruptedException {
        Set<Integer> hashCodes = ConcurrentHashMap.newKeySet();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(() -> {
                ThreadSafeSingleton instance = ThreadSafeSingleton.getInstance();
                hashCodes.add(System.identityHashCode(instance));
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Unique instances for Thread Safe Singleton: " + hashCodes.size());
        assertTrue(hashCodes.size() == 1);
    }

    private long measureExecutionTime(Callable<Object> task) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        long startTime = System.nanoTime();

        Set<Future<Object>> futures = new HashSet<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            futures.add(executor.submit(task));
        }

        for (Future<Object> future : futures) {
            future.get();
        }

        long endTime = System.nanoTime();
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

        return endTime - startTime;
    }

    @Test
    void compareSingletonPerformance() throws InterruptedException, ExecutionException {
        long classicalTime = measureExecutionTime(Singleton::getInstance);
        long threadSafeTime = measureExecutionTime(ThreadSafeSingleton::getInstance);

        System.out.println("Classical Singleton time: " + classicalTime + " ns");
        System.out.println("Thread-Safe Singleton time: " + threadSafeTime + " ns");

        assertTrue(classicalTime > threadSafeTime);
    }
}