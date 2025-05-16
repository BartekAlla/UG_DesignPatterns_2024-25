package pl.edu.ug.factories.builder;


import pl.edu.ug.factories.computation.FactoryComputationObject;
import pl.edu.ug.prototypes.computation.PrototypeComputationObject;

public interface FactoryComputationThreadBuilderInterface<
        A extends PrototypeComputationObject<A>,
        B extends PrototypeComputationObject<B>,
        C extends PrototypeComputationObject<C>> {

    void setComponentA(FactoryComputationObject<A> factoryComputationObject, int amount);

    void setComponentB(FactoryComputationObject<B> factoryComputationObject, int amount);

    void setComponentC(FactoryComputationObject<C> factoryComputationObject, int amount);

    Thread buildThread();
}
