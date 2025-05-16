package prototypes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.ug.prototypes.computation.PrototypeCircleCircumference;
import pl.edu.ug.prototypes.computation.PrototypeComputationPool;

import static org.junit.jupiter.api.Assertions.*;

public class PrototypePoolTest {
    private PrototypeComputationPool<PrototypeCircleCircumference> pool;
    private PrototypeCircleCircumference prototype;

    @BeforeEach
    void setUp() {
        PrototypeComputationPool.resetAll();
        prototype = new PrototypeCircleCircumference(1.0);
        pool = PrototypeComputationPool.getInstance(prototype, 5);
    }

    @Test
    void testAcquireCreatesAndReuses() {
        var a1 = pool.acquire();
        var a2 = pool.acquire();
        assertNotSame(a1, a2);
        assertEquals(2, pool.getCreatedObjectsCount());


        pool.release(a1);
        assertEquals(1, pool.size());
        var a3 = pool.acquire();
        assertSame(a1.getClass(), a3.getClass());
        assertEquals(2, pool.getCreatedObjectsCount());
    }

    @Test
    void testMaxSize() {
        for (int i = 0; i < 10; i++) pool.release(prototype.clone());
        assertEquals(5, pool.size());
    }
}
