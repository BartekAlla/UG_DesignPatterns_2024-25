package pl.edu.ug;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class ThreadSpecificSingletonTest {
    private static final int THREAD_COUNT = 100;


    @Test
    public void testClassicSingleton() throws InterruptedException {
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


        assertTrue(hashCodes.size() != THREAD_COUNT);
    }

    @Test
    public void testThreadSpecificSingleton() throws InterruptedException {
        Set<Integer> hashCodes = ConcurrentHashMap.newKeySet();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(() -> {
                ThreadSpecificSingleton instance = ThreadSpecificSingleton.getInstance();
                hashCodes.add(System.identityHashCode(instance));
            });
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(THREAD_COUNT, hashCodes.size());
    }

}