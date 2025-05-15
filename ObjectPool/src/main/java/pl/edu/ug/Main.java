package pl.edu.ug;

import pl.edu.ug.thread_builder.computation.ComputationPool;
import pl.edu.ug.thread_builder.statistics.BuilderComputationStats;
import pl.edu.ug.thread_builder.builder.ComputationThreadBuilder;
import pl.edu.ug.thread_builder.builder.ComputationThreadDirector;
import pl.edu.ug.thread_builder.computation.CylinderVolume;
import pl.edu.ug.thread_builder.computation.SphereSurfaceArea;
import pl.edu.ug.thread_builder.computation.CircleCircumference;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private final static int THREAD_COUNT = 10000;

    public static void main(String[] args) throws InterruptedException {
        prototypeExperiment(5000);
        System.gc();
        Thread.sleep(500);
        prototypeExperiment(10000);
        System.gc();
        Thread.sleep(500);
        prototypeExperiment(25000);
        System.gc();
        Thread.sleep(500);

    }

    private static void prototypeExperiment(int threadCount) throws InterruptedException {
        System.out.println("\n=== Experiment with " + threadCount + " threads ===");

        List<Thread> threads = new ArrayList<>();
        ComputationThreadDirector director = new ComputationThreadDirector();
        BuilderComputationStats stats = new BuilderComputationStats();

        for (int i = 0; i < threadCount; i++) {
            ComputationThreadBuilder<CircleCircumference, SphereSurfaceArea, CylinderVolume> builder =
                    new ComputationThreadBuilder<>(stats);
            director.constructSimpleComputation(builder);
            Thread t = builder.buildThread();
            threads.add(t);
        }

        threads.forEach(Thread::start);
        for (Thread t : threads) t.join();


        stats.printSummary();
        ComputationPool.resetAll();
    }
}
