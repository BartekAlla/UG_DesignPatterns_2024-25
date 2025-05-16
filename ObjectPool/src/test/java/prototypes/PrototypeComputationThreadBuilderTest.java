package prototypes;

import org.junit.jupiter.api.Test;
import pl.edu.ug.prototypes.builder.PrototypeComputationThreadBuilder;
import pl.edu.ug.prototypes.computation.PrototypeCircleCircumference;
import pl.edu.ug.prototypes.computation.PrototypeComputationPool;
import pl.edu.ug.prototypes.computation.PrototypeCylinderVolume;
import pl.edu.ug.prototypes.computation.PrototypeSphereSurfaceArea;
import pl.edu.ug.prototypes.statistics.PrototypeComputationStats;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrototypeComputationThreadBuilderTest {
    @Test
    void testBuildThreadAndStats() throws InterruptedException {
        PrototypeComputationStats stats = new PrototypeComputationStats();
        PrototypeComputationThreadBuilder<
                PrototypeCircleCircumference,
                PrototypeCylinderVolume,
                PrototypeSphereSurfaceArea
                > builder = new PrototypeComputationThreadBuilder<>(stats);

        builder.setComponentA(new PrototypeCircleCircumference(2.0), 3);
        builder.setComponentB(new PrototypeCylinderVolume(1.0, 2.0), 2);
        builder.setComponentC(new PrototypeSphereSurfaceArea(1.0), 1);

        Thread t = builder.buildThread();
        t.start();
        t.join();

        assertTrue(stats.getTimeA().longValue() > 0);
        assertTrue(stats.getTimeB().longValue() > 0);
        assertTrue(stats.getTimeC().longValue() > 0);

        assertTrue(((PrototypeComputationPool<?>) stats.getPoolA()).getCreatedObjectsCount() <= 3);
    }

    @Test
    void testMultiThreadedPoolSafety() throws InterruptedException {
        PrototypeComputationPool.resetAll();
        var proto = new PrototypeCircleCircumference(1.0);
        var pool = PrototypeComputationPool.getInstance(proto, 50);

        Runnable work = () -> {
            for (int i = 0; i < 1000; i++) {
                var obj = pool.acquire();
                obj.compute();
                pool.release(obj);
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) threads[i] = new Thread(work);
        for (var th : threads) th.start();
        for (var th : threads) th.join();

        assertTrue(pool.size() <= 50);
        assertTrue(pool.getCreatedObjectsCount() <= 10_000);
    }
}
