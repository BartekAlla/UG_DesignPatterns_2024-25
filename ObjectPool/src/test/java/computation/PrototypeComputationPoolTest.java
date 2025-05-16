package computation;

import org.junit.jupiter.api.Test;
import pl.edu.ug.prototypes.computation.PrototypeCircleCircumference;
import pl.edu.ug.prototypes.computation.PrototypeComputationPool;

import static org.junit.jupiter.api.Assertions.*;

class PrototypeComputationPoolTest {

    @Test
    void testPoolAcquire() {
        PrototypeCircleCircumference prototype = new PrototypeCircleCircumference(1.0);
        PrototypeComputationPool<PrototypeCircleCircumference> pool = PrototypeComputationPool.getInstance(prototype, 30);

        PrototypeCircleCircumference obj = pool.acquire();
        assertNotNull(obj);

        pool.release(obj);

    }
    @Test
    void testPoolRealease() {
        PrototypeCircleCircumference prototype = new PrototypeCircleCircumference(1.0);
        PrototypeComputationPool<PrototypeCircleCircumference> pool = PrototypeComputationPool.getInstance(prototype, 30);

        PrototypeCircleCircumference obj = pool.acquire();

        pool.release(obj);
        assertTrue(pool.size() >= 1);
    }
}