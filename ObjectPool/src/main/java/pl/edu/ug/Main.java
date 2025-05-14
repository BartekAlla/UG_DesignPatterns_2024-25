package pl.edu.ug;

import pl.edu.ug.builder.ComputationThreadBuilder;
import pl.edu.ug.builder.ComputationThreadDirector;
import pl.edu.ug.computation.CylinderVolume;
import pl.edu.ug.computation.SphereSurfaceArea;
import pl.edu.ug.computation.CircleCircumference;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private final static int THREAD_COUNT = 50000;
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        ComputationThreadDirector director = new ComputationThreadDirector();

        for (int i = 0; i < THREAD_COUNT; i++) {
            ComputationThreadBuilder<CircleCircumference, SphereSurfaceArea, CylinderVolume> builder = new ComputationThreadBuilder<>();
            director.constructSimpleComputation(builder);
            Thread t = builder.buildThread();
            threads.add(t);
        }
        threads.forEach(Thread::start);
    }
}
