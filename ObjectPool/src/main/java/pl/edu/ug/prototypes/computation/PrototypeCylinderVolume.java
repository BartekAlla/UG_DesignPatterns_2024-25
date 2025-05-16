package pl.edu.ug.prototypes.computation;

public class PrototypeCylinderVolume extends PrototypeComputationObject<PrototypeCylinderVolume> {
    private final double r, h;

    public PrototypeCylinderVolume(double r, double h) {
        this.r = r;
        this.h = h;
    }
    public PrototypeCylinderVolume(PrototypeCylinderVolume target) {
        this.r = target.r;
        this.h = target.h;
    }
    @Override
    public PrototypeCylinderVolume clone() {
        return new PrototypeCylinderVolume(this);
    }

    @Override
    public double compute() {
        return Math.PI * r * r * h;
    }
}
