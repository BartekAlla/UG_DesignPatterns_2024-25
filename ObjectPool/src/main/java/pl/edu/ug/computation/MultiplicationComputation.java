package pl.edu.ug.computation;

public class MultiplicationComputation extends ComputationObject {
    @Override
    public double compute(double input) {
        result = input * config;
        return result;
    }
}