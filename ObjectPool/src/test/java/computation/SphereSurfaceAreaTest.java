package computation;

import org.junit.jupiter.api.Test;
import pl.edu.ug.computation.SphereSurfaceArea;

import static org.junit.jupiter.api.Assertions.*;

class SphereSurfaceAreaTest {

    @Test
    void testComputeSphereSurfaceArea() {
        SphereSurfaceArea s = new SphereSurfaceArea(3.0);
        double expected = 4 * Math.PI * 9;
        assertEquals(expected, s.compute(), 0.0001);
    }
}