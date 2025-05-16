package pl.edu.ug.prototypes.builder;

import pl.edu.ug.prototypes.computation.PrototypeComputationObject;

public interface PrototypeComputationThreadBuilderInterface<
        A extends PrototypeComputationObject<A>,
        B extends PrototypeComputationObject<B>,
        C extends PrototypeComputationObject<C>> {

    void setComponentA(A obj, int amount);

    void setComponentB(B obj, int amount);

    void setComponentC(C obj, int amount);

    Thread buildThread();
}
