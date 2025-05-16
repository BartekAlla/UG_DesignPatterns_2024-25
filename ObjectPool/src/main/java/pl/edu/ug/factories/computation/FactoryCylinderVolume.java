package pl.edu.ug.factories.computation;

import pl.edu.ug.prototypes.computation.PrototypeCylinderVolume;

public class FactoryCylinderVolume implements FactoryComputationObject<PrototypeCylinderVolume> {
    private final double r, h;

    public FactoryCylinderVolume(double r, double h) {
        this.r = r;
        this.h = h;
    }
    @Override
    public PrototypeCylinderVolume createComputationObject() {
        return new PrototypeCylinderVolume(this.r, this.h);
    }
}
