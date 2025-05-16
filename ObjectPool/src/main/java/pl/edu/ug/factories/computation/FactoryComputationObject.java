package pl.edu.ug.factories.computation;


import pl.edu.ug.prototypes.computation.PrototypeComputationObject;

public interface FactoryComputationObject<T extends PrototypeComputationObject<T>> {
    T createComputationObject();
}
