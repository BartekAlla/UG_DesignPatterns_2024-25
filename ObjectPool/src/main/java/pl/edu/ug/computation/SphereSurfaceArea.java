package pl.edu.ug.computation;

public class SphereSurfaceArea extends ComputationObject {
    private final double r;

    public SphereSurfaceArea(double r) {
        this.r = r;
    }

    public SphereSurfaceArea(SphereSurfaceArea target) {
        this.r = target.r;
    }

    @Override
    public SphereSurfaceArea clone() {
        return new SphereSurfaceArea(this);
    }

    @Override
    public double compute() {
        return 4 * Math.PI * r * r;
    }
}