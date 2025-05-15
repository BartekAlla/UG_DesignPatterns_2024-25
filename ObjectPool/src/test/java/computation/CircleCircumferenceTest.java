package computation;

import org.junit.jupiter.api.Test;
import pl.edu.ug.thread_builder.computation.CircleCircumference;

import static org.junit.jupiter.api.Assertions.*;

class CircleCircumferenceTest {

    @Test
    void testComputeCircleCircumference() {
        CircleCircumference c = new CircleCircumference(2.0);
        double expected = 2 * Math.PI * 2.0;
        assertEquals(expected, c.compute(), 0.0001);
    }
}