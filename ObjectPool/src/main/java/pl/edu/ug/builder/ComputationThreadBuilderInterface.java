package pl.edu.ug.builder;

import pl.edu.ug.computation.ComputationObject;

public interface ComputationThreadBuilderInterface<
        A extends ComputationObject<A>,
        B extends ComputationObject<B>,
        C extends ComputationObject<C>> {

    void setComponentA(A obj, int amount);
    void setComponentB(B obj, int amount);
    void setComponentC(C obj, int amount);

    Thread buildThread();
}
