package pl.edu.ug.thread_builder.builder;

import pl.edu.ug.thread_builder.computation.ComputationObject;

public interface ComputationThreadBuilderInterface<
        A extends ComputationObject<A>,
        B extends ComputationObject<B>,
        C extends ComputationObject<C>> {

    void setComponentA(A obj, int amount);
    void setComponentB(B obj, int amount);
    void setComponentC(C obj, int amount);

    Thread buildThread();
}
