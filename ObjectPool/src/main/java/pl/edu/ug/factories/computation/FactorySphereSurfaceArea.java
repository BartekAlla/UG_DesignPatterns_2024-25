package pl.edu.ug.factories.computation;

import pl.edu.ug.prototypes.computation.PrototypeSphereSurfaceArea;

public class FactorySphereSurfaceArea implements FactoryComputationObject<PrototypeSphereSurfaceArea> {
    private final double r;

    public FactorySphereSurfaceArea(double r) {
        this.r = r;
    }

    @Override
    public PrototypeSphereSurfaceArea createComputationObject() {
        return new PrototypeSphereSurfaceArea(this.r);
    }
}