package computation;

import org.junit.jupiter.api.Test;
import pl.edu.ug.prototypes.computation.PrototypeCylinderVolume;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrototypeCylinderVolumeTest {

    @Test
    void testComputeCylinderVolume() {
        PrototypeCylinderVolume c = new PrototypeCylinderVolume(2.0, 5.0);
        double expected = Math.PI * 4 * 5;
        assertEquals(expected, c.compute(), 0.0001);
    }
}