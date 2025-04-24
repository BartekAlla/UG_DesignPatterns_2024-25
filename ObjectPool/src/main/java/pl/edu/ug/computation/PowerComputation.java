package pl.edu.ug.computation;

public class PowerComputation extends ComputationObject {
    @Override
    public double compute(double input) {
        result = Math.pow(input, config);
        return result;
    }
}