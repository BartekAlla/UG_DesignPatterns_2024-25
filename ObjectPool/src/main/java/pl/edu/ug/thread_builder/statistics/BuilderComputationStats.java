package pl.edu.ug.thread_builder.statistics;

import pl.edu.ug.thread_builder.computation.ComputationPool;
import java.util.concurrent.atomic.LongAdder;
public class BuilderComputationStats {

    private final LongAdder timeA = new LongAdder();
    private final LongAdder countA = new LongAdder();

    private final LongAdder timeB = new LongAdder();
    private final LongAdder countB = new LongAdder();

    private final LongAdder timeC = new LongAdder();
    private final LongAdder countC = new LongAdder();

    private ComputationPool<?> poolA;
    private ComputationPool<?> poolB;
    private ComputationPool<?> poolC;

    public void setPools(ComputationPool<?> a, ComputationPool<?> b, ComputationPool<?> c) {
        this.poolA = a;
        this.poolB = b;
        this.poolC = c;
    }

    public void reportTimeAndCreations(long timeA_ns, int countA, long timeB_ns, int countB, long timeC_ns, int countC) {
        this.timeA.add(timeA_ns);
        this.countA.add(countA);


        this.timeB.add(timeB_ns);
        this.countB.add(countB);


        this.timeC.add(timeC_ns);
        this.countC.add(countC);

    }

    public void printSummary() {
        System.out.printf("CircleCircumference: avg time %.2f µs, tasks: %d, created objects: %d, free in pool: %d%n",
                timeA.doubleValue() / countA.doubleValue() / 1000,
                countA.intValue(),
                poolA != null ? poolA.getCreatedObjectsCount() : -1,
                poolA != null ? poolA.size() : -1);

        System.out.printf("SphereSurfaceArea: avg time %.2f µs, tasks: %d, created objects: %d, free in pool: %d%n",
                timeB.doubleValue() / countB.doubleValue() / 1000,
                countB.intValue(),
                poolB != null ? poolB.getCreatedObjectsCount() : -1,
                poolB != null ? poolB.size() : -1);

        System.out.printf("CylinderVolume: avg time %.2f µs, tasks: %d, created objects: %d, free in pool: %d%n",
                timeC.doubleValue() / countC.doubleValue() / 1000,
                countC.intValue(),
                poolC != null ? poolC.getCreatedObjectsCount() : -1,
                poolC != null ? poolC.size() : -1);
    }
}