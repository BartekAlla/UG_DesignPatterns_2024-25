package pl.edu.ug.computation;


public abstract class ComputationObject implements Cloneable {
    protected double config;
    protected double result;

    public void setConfig(double config) {
        this.config = config;
    }

    public double getResult() {
        return result;
    }

    public abstract double compute(double input);

    @Override
    public ComputationObject clone() {
        try {
            return (ComputationObject) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone failed", e);
        }
    }
}
