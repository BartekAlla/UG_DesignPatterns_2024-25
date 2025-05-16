package pl.edu.ug.prototypes.computation;


public abstract class PrototypeComputationObject<T> implements Cloneable {
    public abstract T clone();

    public abstract double compute();
}
