package pl.edu.ug.prototypes.statistics;

import pl.edu.ug.prototypes.computation.PrototypeComputationPool;
import java.util.concurrent.atomic.LongAdder;
public class PrototypeComputationStats {

    private final LongAdder timeA = new LongAdder();
    private final LongAdder countA = new LongAdder();

    private final LongAdder timeB = new LongAdder();
    private final LongAdder countB = new LongAdder();

    private final LongAdder timeC = new LongAdder();
    private final LongAdder countC = new LongAdder();

    private PrototypeComputationPool<?> poolA;
    private PrototypeComputationPool<?> poolB;
    private PrototypeComputationPool<?> poolC;



    public void setPools(PrototypeComputationPool<?> a, PrototypeComputationPool<?> b, PrototypeComputationPool<?> c) {
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

        double totalA_ms = timeA.doubleValue() / 1_000_000;
        double totalB_ms = timeB.doubleValue() / 1_000_000;
        double totalC_ms = timeC.doubleValue() / 1_000_000;

        System.out.printf("CircleCircumference:%n total time %.2f ms, avg time %.2f µs, tasks: %d, created objects: %d, free in pool: %d%n",
                totalA_ms,
                timeA.doubleValue() / countA.doubleValue() / 1000,
                countA.intValue(),
                poolA != null ? poolA.getCreatedObjectsCount() : -1,
                poolA != null ? poolA.size() : -1);

        System.out.printf("SphereSurfaceArea:%n total time %.2f ms, avg time %.2f µs, tasks: %d, created objects: %d, free in pool: %d%n",
                totalB_ms,
                timeB.doubleValue() / countB.doubleValue() / 1000,
                countB.intValue(),
                poolB != null ? poolB.getCreatedObjectsCount() : -1,
                poolB != null ? poolB.size() : -1);

        System.out.printf("CylinderVolume:%n total time %.2f ms, avg time %.2f µs, tasks: %d, created objects: %d, free in pool: %d%n",
                totalC_ms,
                timeC.doubleValue() / countC.doubleValue() / 1000,
                countC.intValue(),
                poolC != null ? poolC.getCreatedObjectsCount() : -1,
                poolC != null ? poolC.size() : -1);
    }
}