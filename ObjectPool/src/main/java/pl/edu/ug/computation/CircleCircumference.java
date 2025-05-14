package pl.edu.ug.computation;

public class CircleCircumference extends ComputationObject {
    private final double r;

    public CircleCircumference(double r) {
        this.r = r;
    }

    public CircleCircumference(CircleCircumference target) {
        this.r = target.r;
    }

    @Override
    public CircleCircumference clone() {
        return new CircleCircumference(this);
    }

    @Override
    public double compute() {
        return 2 * Math.PI * r;
    }
}