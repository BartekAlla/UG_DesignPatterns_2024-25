package factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.ug.factories.computation.FactoryCircleCircumference;
import pl.edu.ug.factories.computation.FactoryComputationPool;
import pl.edu.ug.prototypes.computation.PrototypeCircleCircumference;

import static org.junit.jupiter.api.Assertions.*;

public class FactoryPoolTest {
    private FactoryComputationPool<PrototypeCircleCircumference> pool;

    @BeforeEach
    void setUp() {
        FactoryComputationPool.resetAll();
        pool = FactoryComputationPool.getInstance(new FactoryCircleCircumference(1.0));
    }

    @Test
    void testAcquireCreatesAndReuses() {
        var a1 = pool.getObject();
        var a2 = pool.getObject();
        assertNotSame(a1, a2);
        assertEquals(2, pool.getCreatedObjectsCount());

        pool.releaseObject(a1);
        assertEquals(1, pool.size());

        var a3 = pool.getObject();
        assertSame(a1.getClass(), a3.getClass());
        assertEquals(2, pool.getCreatedObjectsCount());
    }

    @Test
    void testMultiThreadedPoolSafety() throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                var obj = pool.getObject();
                obj.compute();
                pool.releaseObject(obj);
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(task);
        }

        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();

        assertTrue(pool.getCreatedObjectsCount() <= 10_000);
    }
}
