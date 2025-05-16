package pl.edu.ug.prototypes.computation;

public class PrototypeCircleCircumference extends PrototypeComputationObject<PrototypeCircleCircumference> {
    private final double r;

    public PrototypeCircleCircumference(double r) {
        this.r = r;
    }

    public PrototypeCircleCircumference(PrototypeCircleCircumference target) {
        this.r = target.r;
    }

    @Override
    public PrototypeCircleCircumference clone() {
        return new PrototypeCircleCircumference(this);
    }

    @Override
    public double compute() {
        return 2 * Math.PI * r;
    }
}