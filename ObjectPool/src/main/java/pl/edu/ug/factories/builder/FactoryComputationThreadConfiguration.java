package pl.edu.ug.factories.builder;

import pl.edu.ug.factories.computation.FactoryCircleCircumference;
import pl.edu.ug.factories.computation.FactoryCylinderVolume;
import pl.edu.ug.factories.computation.FactorySphereSurfaceArea;


public class FactoryComputationThreadConfiguration {
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void constructSimpleComputation(FactoryComputationThreadBuilderInterface builder) {
        builder.setComponentA(new FactoryCircleCircumference(5), 2);
        builder.setComponentB(new FactoryCylinderVolume(2, 10), 1);
        builder.setComponentC(new FactorySphereSurfaceArea(3), 3);
    }
}
