package pl.edu.ug.thread_builder.computation;


public abstract class ComputationObject<T> implements Cloneable {
    public abstract T clone();
    public abstract double compute();
}
