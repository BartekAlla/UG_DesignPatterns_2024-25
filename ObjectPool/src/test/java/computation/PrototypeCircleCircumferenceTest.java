package computation;

import org.junit.jupiter.api.Test;
import pl.edu.ug.prototypes.computation.PrototypeCircleCircumference;

import static org.junit.jupiter.api.Assertions.*;

class PrototypeCircleCircumferenceTest {

    @Test
    void testComputeCircleCircumference() {
        PrototypeCircleCircumference c = new PrototypeCircleCircumference(2.0);
        double expected = 2 * Math.PI * 2.0;
        assertEquals(expected, c.compute(), 0.0001);
    }
}