
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.ug.computation.CylinderVolume;
import pl.edu.ug.computation.ComputationObject;
import pl.edu.ug.computation.SphereSurfaceArea;
import pl.edu.ug.computation.CircleCircumference;
import pl.edu.ug.computation.ComputationPool;


import static org.junit.jupiter.api.Assertions.*;

public class ComputationalPoolTests {
    private ComputationPool<CylinderVolume> addPool;
    private ComputationPool<SphereSurfaceArea> mulPool;
    private ComputationPool<CircleCircumference> powPool;

    @BeforeEach
    void setUp() {
        addPool = new ComputationPool<>(new CylinderVolume(), 2);
        mulPool = new ComputationPool<>(new SphereSurfaceArea(), 2);
        powPool = new ComputationPool<>(new CircleCircumference(), 2);
    }

    @Test
    void testAdditionComputation() {
        CylinderVolume obj = addPool.acquire();
        obj.setConfig(10);
        double result = obj.compute(5);
        assertEquals(15.0, result);
        addPool.release(obj);
    }

    @Test
    void testMultiplicationComputation() {
        SphereSurfaceArea obj = mulPool.acquire();
        obj.setConfig(4);
        double result = obj.compute(3);
        assertEquals(12.0, result);
        mulPool.release(obj);
    }

    @Test
    void testPowerComputation() {
        CircleCircumference obj = powPool.acquire();
        obj.setConfig(3);
        double result = obj.compute(2);
        assertEquals(8.0, result);
        powPool.release(obj);
    }

    @Test
    void testPoolGrowsWhenEmpty() {
        ComputationObject obj1 = addPool.acquire();
        ComputationObject obj2 = addPool.acquire();
        ComputationObject obj3 = addPool.acquire(); // should clone

        assertNotNull(obj3);
        assertNotSame(obj1, obj3);
    }
}
