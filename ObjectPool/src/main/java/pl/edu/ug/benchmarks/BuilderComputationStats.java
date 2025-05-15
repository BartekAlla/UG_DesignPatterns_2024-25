package pl.edu.ug.benchmarks;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.DoubleAdder;

public class BuilderComputationStats {

    private final DoubleAdder sumA = new DoubleAdder();
    private final DoubleAdder sumB = new DoubleAdder();
    private final DoubleAdder sumC = new DoubleAdder();

    private final AtomicInteger tasksA = new AtomicInteger();
    private final AtomicInteger tasksB = new AtomicInteger();
    private final AtomicInteger tasksC = new AtomicInteger();

    public void report(double resultA, int countA, double resultB, int countB, double resultC, int countC) {
        sumA.add(resultA);
        sumB.add(resultB);
        sumC.add(resultC);

        tasksA.addAndGet(countA);
        tasksB.addAndGet(countB);
        tasksC.addAndGet(countC);
    }

    public void printSummary(int poolSizeA, int poolSizeB, int poolSizeC) {
        System.out.printf("CircleCircumference: %.2f (Total Tasks: %d) | Pool size: %d\n",
                sumA.sum(), tasksA.get(), poolSizeA);
        System.out.printf("SphereSurfaceArea: %.2f (Total Tasks: %d) | Pool size: %d\n",
                sumB.sum(), tasksB.get(), poolSizeB);
        System.out.printf("CylinderVolume: %.2f (Total Tasks: %d) | Pool size: %d\n",
                sumC.sum(), tasksC.get(), poolSizeC);
        System.out.println("ALL TASKS DONE.\n");
    }
}