package pl.edu.ug;

import pl.edu.ug.factories.builder.FactoryComputationThreadBuilder;
import pl.edu.ug.factories.builder.FactoryComputationThreadConfiguration;
import pl.edu.ug.factories.computation.FactoryComputationPool;
import pl.edu.ug.factories.statistics.FactoryComputationStats;
import pl.edu.ug.prototypes.builder.PrototypeComputationThreadBuilder;
import pl.edu.ug.prototypes.builder.PrototypeComputationThreadConfiguration;
import pl.edu.ug.prototypes.computation.PrototypeCircleCircumference;
import pl.edu.ug.prototypes.computation.PrototypeComputationPool;
import pl.edu.ug.prototypes.computation.PrototypeCylinderVolume;
import pl.edu.ug.prototypes.computation.PrototypeSphereSurfaceArea;
import pl.edu.ug.prototypes.statistics.PrototypeComputationStats;

import java.util.ArrayList;
import java.util.List;

public class Main {

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
        factoryExperiment(5000);
        System.gc();
        Thread.sleep(500);
        factoryExperiment(10000);
        System.gc();
        Thread.sleep(500);
        factoryExperiment(25000);
        System.gc();
        Thread.sleep(500);

    }

    private static void prototypeExperiment(int threadCount) throws InterruptedException {
        System.out.println("\n=== Prototype Experiment with " + threadCount + " threads ===");

        List<Thread> threads = new ArrayList<>();
        PrototypeComputationThreadConfiguration director = new PrototypeComputationThreadConfiguration();
        PrototypeComputationStats stats = new PrototypeComputationStats();

        for (int i = 0; i < threadCount; i++) {
            PrototypeComputationThreadBuilder<PrototypeCircleCircumference, PrototypeSphereSurfaceArea, PrototypeCylinderVolume> builder =
                    new PrototypeComputationThreadBuilder<>(stats);
            director.constructSimpleComputation(builder);
            Thread t = builder.buildThread();
            threads.add(t);
        }

        threads.forEach(Thread::start);
        for (Thread t : threads) t.join();

        stats.printSummary();
        PrototypeComputationPool.resetAll();
    }
    private static void factoryExperiment(int threadCount) throws InterruptedException {
        System.out.println("\n=== Fabric Experiment with " + threadCount + " threads ===");

        List<Thread> threads = new ArrayList<>();
        FactoryComputationThreadConfiguration director = new FactoryComputationThreadConfiguration();
        FactoryComputationStats stats = new FactoryComputationStats();

        for (int i = 0; i < threadCount; i++) {
            FactoryComputationThreadBuilder<PrototypeCircleCircumference, PrototypeSphereSurfaceArea, PrototypeCylinderVolume> builder =
                    new FactoryComputationThreadBuilder<>(stats);
            director.constructSimpleComputation(builder);
            Thread t = builder.buildThread();
            threads.add(t);
        }

        threads.forEach(Thread::start);
        for (Thread t : threads) t.join();

        stats.printSummary();
        FactoryComputationPool.resetAll();
    }
}
