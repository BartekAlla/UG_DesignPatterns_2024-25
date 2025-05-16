package pl.edu.ug.prototypes.builder;


import pl.edu.ug.prototypes.computation.PrototypeComputationObject;
import pl.edu.ug.prototypes.computation.PrototypeComputationPool;
import pl.edu.ug.prototypes.statistics.PrototypeComputationStats;

import java.util.ArrayList;
import java.util.List;

public class PrototypeComputationThreadBuilder<
        A extends PrototypeComputationObject<A>,
        B extends PrototypeComputationObject<B>,
        C extends PrototypeComputationObject<C>>
        implements PrototypeComputationThreadBuilderInterface<A, B, C> {

    private A prototypeA;
    private int countA;

    private B prototypeB;
    private int countB;

    private C prototypeC;
    private int countC;

    private final PrototypeComputationStats stats;

    public PrototypeComputationThreadBuilder(PrototypeComputationStats stats) {
        this.stats = stats;
    }

    @Override
    public void setComponentA(A obj, int amount) {
        this.prototypeA = obj;
        this.countA = amount;
    }

    @Override
    public void setComponentB(B obj, int amount) {
        this.prototypeB = obj;
        this.countB = amount;
    }

    @Override
    public void setComponentC(C obj, int amount) {
        this.prototypeC = obj;
        this.countC = amount;
    }

    @Override
    public Thread buildThread() {
        return new Thread(() -> {
            PrototypeComputationPool<A> poolA = PrototypeComputationPool.getInstance(prototypeA, 9999999);
            PrototypeComputationPool<B> poolB = PrototypeComputationPool.getInstance(prototypeB, 9999999);
            PrototypeComputationPool<C> poolC = PrototypeComputationPool.getInstance(prototypeC, 9999999);

            stats.setPools(poolA, poolB, poolC);

            List<A> listA = new ArrayList<>();
            List<B> listB = new ArrayList<>();
            List<C> listC = new ArrayList<>();

                for (int i = 0; i < countA; i++) listA.add(poolA.acquire());
                for (int i = 0; i < countB; i++) listB.add(poolB.acquire());
                for (int i = 0; i < countC; i++) listC.add(poolC.acquire());

                long startA = System.nanoTime();
                listA.forEach(A::compute);

                long startB = System.nanoTime();
                listB.forEach(B::compute);

                long startC = System.nanoTime();
                listC.forEach(C::compute);


                listA.forEach(poolA::release);
                long timeA = System.nanoTime() - startA;
                listB.forEach(poolB::release);
                long timeB = System.nanoTime() - startB;
                listC.forEach(poolC::release);
                long timeC = System.nanoTime() - startC;
                stats.reportTimeAndCreations(timeA, countA, timeB, countB, timeC, countC);
        });
    }
}