package pl.edu.ug.factories.computation;


import pl.edu.ug.prototypes.computation.PrototypeCircleCircumference;

public class FactoryCircleCircumference implements FactoryComputationObject<PrototypeCircleCircumference> {
    private final double r;

    public FactoryCircleCircumference(double r) {
        this.r = r;
    }

    @Override
    public PrototypeCircleCircumference createComputationObject() {
        return new PrototypeCircleCircumference(this.r);
    }
}