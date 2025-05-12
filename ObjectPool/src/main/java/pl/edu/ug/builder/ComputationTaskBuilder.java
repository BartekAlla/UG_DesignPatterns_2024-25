package pl.edu.ug.builder;

import pl.edu.ug.computation.ComputationObject;
import pl.edu.ug.computation.ComputationPool;

import java.util.ArrayList;
import java.util.List;

public class ComputationTaskBuilder {
    private final List<ComputationPool<? extends ComputationObject>> pools = new ArrayList<>();
    private final List<Double> configs = new ArrayList<>();
    private final List<Double> inputs = new ArrayList<>();

    public ComputationTaskBuilder add(ComputationPool<? extends ComputationObject> pool, double config, double input) {
        pools.add(pool);
        configs.add(config);
        inputs.add(input);
        return this;
    }

    public Runnable build() {
        return () -> {
            List<Object[]> pooledData = new ArrayList<>();

            for (int i = 0; i < pools.size(); i++) {
                ComputationPool<? extends ComputationObject> pool = pools.get(i);
                ComputationObject obj = pool.acquire();
                obj.setConfig(configs.get(i));
                obj.compute(inputs.get(i));
                pooledData.add(new Object[]{pool, obj});
            }

            for (Object[] pair : pooledData) {
                ComputationObject obj = (ComputationObject) pair[1];
                System.out.println(Thread.currentThread().getName() + " Result: " + obj.getResult());
            }

            for (Object[] pair : pooledData) {
                ComputationPool pool = (ComputationPool) pair[0];
                pool.release((ComputationObject) pair[1]);
            }
        };
    }
}
