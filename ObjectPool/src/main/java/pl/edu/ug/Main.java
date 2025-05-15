package pl.edu.ug;

import pl.edu.ug.benchmarks.BuilderComputationStats;
import pl.edu.ug.builder.ComputationThreadBuilder;
import pl.edu.ug.builder.ComputationThreadDirector;
import pl.edu.ug.computation.ComputationPool;
import pl.edu.ug.computation.CylinderVolume;
import pl.edu.ug.computation.SphereSurfaceArea;
import pl.edu.ug.computation.CircleCircumference;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private final static int THREAD_COUNT = 10000;

    public static void main(String[] args) throws InterruptedException {

        List<Thread> threads = new ArrayList<>();
        ComputationThreadDirector director = new ComputationThreadDirector();
        BuilderComputationStats stats = new BuilderComputationStats();

        for (int i = 0; i < THREAD_COUNT; i++) {
            ComputationThreadBuilder<CircleCircumference, SphereSurfaceArea, CylinderVolume> builder =
                    new ComputationThreadBuilder<>(stats);
            director.constructSimpleComputation(builder);
            Thread t = builder.buildThread();
            threads.add(t);
        }

        threads.forEach(Thread::start);
        for (Thread t : threads) t.join();

        var poolA = ComputationPool.getInstance(new CircleCircumference(1), 30);
        var poolB = ComputationPool.getInstance(new SphereSurfaceArea(1), 30);
        var poolC = ComputationPool.getInstance(new CylinderVolume(1, 1), 30);

        stats.printSummary(poolA.size(), poolB.size(), poolC.size());
    }

}
