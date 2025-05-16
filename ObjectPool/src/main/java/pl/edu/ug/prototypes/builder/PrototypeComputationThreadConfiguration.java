package pl.edu.ug.prototypes.builder;

import pl.edu.ug.prototypes.computation.PrototypeCircleCircumference;
import pl.edu.ug.prototypes.computation.PrototypeCylinderVolume;
import pl.edu.ug.prototypes.computation.PrototypeSphereSurfaceArea;

public class PrototypeComputationThreadConfiguration {
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void constructSimpleComputation(PrototypeComputationThreadBuilderInterface builder) {
        builder.setComponentA(new PrototypeCircleCircumference(5), 2);
        builder.setComponentB(new PrototypeCylinderVolume(2, 10), 1);
        builder.setComponentC(new PrototypeSphereSurfaceArea(3), 3);
    }
}
