package computation;

import org.junit.jupiter.api.Test;
import pl.edu.ug.thread_builder.computation.CylinderVolume;

import static org.junit.jupiter.api.Assertions.*;

class CylinderVolumeTest {

    @Test
    void testComputeCylinderVolume() {
        CylinderVolume c = new CylinderVolume(2.0, 5.0);
        double expected = Math.PI * 4 * 5;
        assertEquals(expected, c.compute(), 0.0001);
    }
}