package factories;


import org.junit.jupiter.api.Test;
import pl.edu.ug.factories.builder.FactoryComputationThreadBuilder;
import pl.edu.ug.factories.computation.FactoryCircleCircumference;
import pl.edu.ug.factories.computation.FactoryCylinderVolume;
import pl.edu.ug.factories.computation.FactorySphereSurfaceArea;
import pl.edu.ug.factories.statistics.FactoryComputationStats;
import pl.edu.ug.prototypes.computation.PrototypeCircleCircumference;
import pl.edu.ug.prototypes.computation.PrototypeCylinderVolume;
import pl.edu.ug.prototypes.computation.PrototypeSphereSurfaceArea;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FactoryComputationThreadBuildTest {
    @Test
    void testBuildThreadAndStats() throws InterruptedException {
        FactoryComputationStats stats = new FactoryComputationStats();
        FactoryComputationThreadBuilder<
                PrototypeCircleCircumference,
                PrototypeCylinderVolume,
                PrototypeSphereSurfaceArea> builder =
                new FactoryComputationThreadBuilder<>(stats);

        builder.setComponentA(new FactoryCircleCircumference(2.0), 3);
        builder.setComponentB(new FactoryCylinderVolume(1.0, 2.0), 2);
        builder.setComponentC(new FactorySphereSurfaceArea(1.0), 1);

        Thread t = builder.buildThread();
        t.start();
        t.join();

        assertTrue(stats.getTimeA() > 0);
        assertTrue(stats.getTimeB() > 0);
        assertTrue(stats.getTimeC() > 0);


        assertTrue(stats.getPoolA().getCreatedObjectsCount() <= 3);
        assertTrue(stats.getPoolB().getCreatedObjectsCount() <= 2);
        assertTrue(stats.getPoolC().getCreatedObjectsCount() <= 1);

    }

}
