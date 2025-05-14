package pl.edu.ug.builder;

import pl.edu.ug.computation.CircleCircumference;
import pl.edu.ug.computation.CylinderVolume;
import pl.edu.ug.computation.SphereSurfaceArea;

public class ComputationThreadDirector {
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void constructSimpleComputation(ComputationThreadBuilderInterface builder) {
        builder.setComponentA(new CircleCircumference(5), 2);
        builder.setComponentB(new CylinderVolume(2, 10), 1);
        builder.setComponentC(new SphereSurfaceArea(3), 1);
    }
}
