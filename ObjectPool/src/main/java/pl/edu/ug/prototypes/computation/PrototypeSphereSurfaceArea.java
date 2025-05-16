package pl.edu.ug.prototypes.computation;

public class PrototypeSphereSurfaceArea extends PrototypeComputationObject<PrototypeSphereSurfaceArea> {
    private final double r;

    public PrototypeSphereSurfaceArea(double r) {
        this.r = r;
    }

    public PrototypeSphereSurfaceArea(PrototypeSphereSurfaceArea target) {
        this.r = target.r;
    }

    @Override
    public PrototypeSphereSurfaceArea clone() {
        return new PrototypeSphereSurfaceArea(this);
    }

    @Override
    public double compute() {
        return 4 * Math.PI * r * r;
    }
}