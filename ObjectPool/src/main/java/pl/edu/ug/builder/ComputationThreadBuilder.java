package pl.edu.ug.builder;


import pl.edu.ug.benchmarks.BuilderComputationStats;
import pl.edu.ug.computation.*;

import java.util.ArrayList;
import java.util.List;

public class ComputationThreadBuilder<
        A extends ComputationObject<A>,
        B extends ComputationObject<B>,
        C extends ComputationObject<C>>
        implements ComputationThreadBuilderInterface<A, B, C> {

    private A prototypeA;
    private int countA;

    private B prototypeB;
    private int countB;

    private C prototypeC;
    private int countC;

    private final BuilderComputationStats stats;

    public ComputationThreadBuilder(BuilderComputationStats stats) {
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
            ComputationPool<A> poolA = ComputationPool.getInstance(prototypeA, 30);
            ComputationPool<B> poolB = ComputationPool.getInstance(prototypeB, 30);
            ComputationPool<C> poolC = ComputationPool.getInstance(prototypeC, 30);

            List<A> listA = new ArrayList<>();
            List<B> listB = new ArrayList<>();
            List<C> listC = new ArrayList<>();
            try {
                for (int i = 0; i < countA; i++) listA.add(poolA.acquire());
                for (int i = 0; i < countB; i++) listB.add(poolB.acquire());
                for (int i = 0; i < countC; i++) listC.add(poolC.acquire());

                double resultA = listA.stream().mapToDouble(A::compute).sum();
                double resultB = listB.stream().mapToDouble(B::compute).sum();
                double resultC = listC.stream().mapToDouble(C::compute).sum();


                stats.report(resultA, countA, resultB, countB, resultC, countC);
            } finally {
                listA.forEach(poolA::release);
                listB.forEach(poolB::release);
                listC.forEach(poolC::release);

            }
        });
    }
}