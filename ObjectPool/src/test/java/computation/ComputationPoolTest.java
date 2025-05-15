package computation;

import org.junit.jupiter.api.Test;
import pl.edu.ug.computation.CircleCircumference;
import pl.edu.ug.computation.ComputationPool;

import static org.junit.jupiter.api.Assertions.*;

class ComputationPoolTest {

    @Test
    void testPoolAcquire() {
        CircleCircumference prototype = new CircleCircumference(1.0);
        ComputationPool<CircleCircumference> pool = ComputationPool.getInstance(prototype, 30);

        CircleCircumference obj = pool.acquire();
        assertNotNull(obj);

        pool.release(obj);

    }
    @Test
    void testPoolRealease() {
        CircleCircumference prototype = new CircleCircumference(1.0);
        ComputationPool<CircleCircumference> pool = ComputationPool.getInstance(prototype, 30);

        CircleCircumference obj = pool.acquire();

        pool.release(obj);
        assertTrue(pool.size() >= 1);
    }
}