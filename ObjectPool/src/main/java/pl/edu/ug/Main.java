package pl.edu.ug;

import pl.edu.ug.builder.ComputationTaskBuilder;
import pl.edu.ug.computation.CylinderVolume;
import pl.edu.ug.computation.SphereSurfaceArea;
import pl.edu.ug.computation.CircleCircumference;
import pl.edu.ug.computation.ComputationPool;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ComputationPool<CylinderVolume> addPool = new ComputationPool<>(new CylinderVolume(), 3);
        ComputationPool<SphereSurfaceArea> mulPool = new ComputationPool<>(new SphereSurfaceArea(), 3);
        ComputationPool<CircleCircumference> powPool = new ComputationPool<>(new CircleCircumference(), 3);

        int numThreads = 10;
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            ComputationTaskBuilder builder = new ComputationTaskBuilder()
                    .add(addPool, 10 + i, i)
                    .add(mulPool, 2 + i, i)
                    .add(powPool, 2, i);
            threads[i] = new Thread(builder.build(), "Thread-" + i);
        }

        long start = System.currentTimeMillis();
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();
        long end = System.currentTimeMillis();

        System.out.println("Execution time: " + (end - start) + "ms");
    }
}
