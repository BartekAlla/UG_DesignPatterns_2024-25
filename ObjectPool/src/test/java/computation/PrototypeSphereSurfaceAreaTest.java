package computation;

import org.junit.jupiter.api.Test;
import pl.edu.ug.prototypes.computation.PrototypeSphereSurfaceArea;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrototypeSphereSurfaceAreaTest {

    @Test
    void testComputeSphereSurfaceArea() {
        PrototypeSphereSurfaceArea s = new PrototypeSphereSurfaceArea(3.0);
        double expected = 4 * Math.PI * 9;
        assertEquals(expected, s.compute(), 0.0001);
    }
}