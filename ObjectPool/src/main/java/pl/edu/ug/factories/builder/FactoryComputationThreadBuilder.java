package pl.edu.ug.factories.builder;


import pl.edu.ug.factories.computation.FactoryComputationObject;
import pl.edu.ug.factories.computation.FactoryComputationPool;
import pl.edu.ug.factories.statistics.FactoryComputationStats;
import pl.edu.ug.prototypes.computation.PrototypeComputationObject;

import java.util.ArrayList;
import java.util.List;

public class FactoryComputationThreadBuilder<
        A extends PrototypeComputationObject<A>,
        B extends PrototypeComputationObject<B>,
        C extends PrototypeComputationObject<C>>
        implements FactoryComputationThreadBuilderInterface<A, B, C> {

    private int countA;

    private int countB;

    private int countC;

    private final FactoryComputationStats stats;
    private FactoryComputationObject<A> factoryA;
    private FactoryComputationObject<B> factoryB;
    private FactoryComputationObject<C> factoryC;

    public FactoryComputationThreadBuilder(FactoryComputationStats stats) {
        this.stats = stats;
    }

    @Override
    public void setComponentA(FactoryComputationObject<A> factoryComputationObject, int amount) {
        this.factoryA = factoryComputationObject;
        this.countA = amount;
    }

    @Override
    public void setComponentB(FactoryComputationObject<B> factoryComputationObject, int amount) {
        this.factoryB = factoryComputationObject;
        this.countB = amount;
    }

    @Override
    public void setComponentC(FactoryComputationObject<C> factoryComputationObject, int amount) {
        this.factoryC = factoryComputationObject;
        this.countC = amount;
    }

    @Override
    public Thread buildThread() {
        return new Thread(() -> {
            FactoryComputationPool<A> poolA = FactoryComputationPool.getInstance(factoryA);
            FactoryComputationPool<B> poolB = FactoryComputationPool.getInstance(factoryB);
            FactoryComputationPool<C> poolC = FactoryComputationPool.getInstance(factoryC);

            stats.setPools(poolA, poolB, poolC);

            List<A> listA = new ArrayList<>();
            List<B> listB = new ArrayList<>();
            List<C> listC = new ArrayList<>();

            for (int i = 0; i < countA; i++) listA.add(poolA.getObject());
            for (int i = 0; i < countB; i++) listB.add(poolB.getObject());
            for (int i = 0; i < countC; i++) listC.add(poolC.getObject());

            long startA = System.nanoTime();
            listA.forEach(A::compute);
            long timeA = System.nanoTime() - startA;

            long startB = System.nanoTime();
            listB.forEach(B::compute);
            long timeB = System.nanoTime() - startB;

            long startC = System.nanoTime();
            listC.forEach(C::compute);
            long timeC = System.nanoTime() - startC;

            listA.forEach(poolA::releaseObject);
            listB.forEach(poolB::releaseObject);
            listC.forEach(poolC::releaseObject);

            stats.reportTimeAndCreations(timeA, countA, timeB, countB, timeC, countC);
        });
    }
}