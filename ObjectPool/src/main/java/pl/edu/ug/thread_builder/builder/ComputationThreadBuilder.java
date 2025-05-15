package pl.edu.ug.thread_builder.builder;


import pl.edu.ug.thread_builder.computation.ComputationObject;
import pl.edu.ug.thread_builder.computation.ComputationPool;
import pl.edu.ug.thread_builder.statistics.BuilderComputationStats;
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
            ComputationPool<A> poolA = ComputationPool.getInstance(prototypeA, 9999999);
            ComputationPool<B> poolB = ComputationPool.getInstance(prototypeB, 9999999);
            ComputationPool<C> poolC = ComputationPool.getInstance(prototypeC, 9999999);

            stats.setPools(poolA, poolB, poolC);

            List<A> listA = new ArrayList<>();
            List<B> listB = new ArrayList<>();
            List<C> listC = new ArrayList<>();

           // try {
                for (int i = 0; i < countA; i++) listA.add(poolA.acquire());
                for (int i = 0; i < countB; i++) listB.add(poolB.acquire());
                for (int i = 0; i < countC; i++) listC.add(poolC.acquire());

                long startA = System.nanoTime();
                listA.forEach(A::compute);
//                long timeA = System.nanoTime() - startA;

                long startB = System.nanoTime();
                listB.forEach(B::compute);
//                long timeB = System.nanoTime() - startB;

                long startC = System.nanoTime();
                listC.forEach(C::compute);
//                long timeC = System.nanoTime() - startC;

               // stats.reportTimeAndCreations(timeA, countA, timeB, countB, timeC, countC);

           // } finally {
                listA.forEach(poolA::release);
            long timeA = System.nanoTime() - startA;
                listB.forEach(poolB::release);
            long timeB = System.nanoTime() - startB;
                listC.forEach(poolC::release);
            long timeC = System.nanoTime() - startC;
            stats.reportTimeAndCreations(timeA, countA, timeB, countB, timeC, countC);
           // }
        });
    }
}