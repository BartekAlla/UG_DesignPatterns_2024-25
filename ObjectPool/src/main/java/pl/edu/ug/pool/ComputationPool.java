package pl.edu.ug.pool;

import pl.edu.ug.computation.ComputationObject;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ComputationPool<T extends ComputationObject> {
    private final Queue<T> pool = new ConcurrentLinkedQueue<>();
    private final T prototype;

    public ComputationPool(T prototype, int initialSize) {
        this.prototype = prototype;
        for (int i = 0; i < initialSize; i++) {
            pool.add((T) prototype.clone());
        }
    }

    public T acquire() {
        T obj = pool.poll();
        if (obj == null) {
            return (T) prototype.clone();
        }
        return obj;
    }

    public void release(T obj) {
        pool.add(obj);
    }
}
