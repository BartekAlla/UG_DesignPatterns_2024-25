package pl.edu.ug;

import pl.edu.ug.builder.ComputationTaskBuilder;
import pl.edu.ug.computation.AdditionComputation;
import pl.edu.ug.computation.MultiplicationComputation;
import pl.edu.ug.computation.PowerComputation;
import pl.edu.ug.pool.ComputationPool;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ComputationPool<AdditionComputation> addPool = new ComputationPool<>(new AdditionComputation(), 3);
        ComputationPool<MultiplicationComputation> mulPool = new ComputationPool<>(new MultiplicationComputation(), 3);
        ComputationPool<PowerComputation> powPool = new ComputationPool<>(new PowerComputation(), 3);

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
