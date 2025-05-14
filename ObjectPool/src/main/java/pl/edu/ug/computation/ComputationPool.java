package pl.edu.ug.computation;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ComputationPool<T extends ComputationObject<T>> {
    private final ConcurrentLinkedQueue<T> pool = new ConcurrentLinkedQueue<>();
    private final T prototype;
    private static final Map<Class<?>, ComputationPool<?>> instances = new HashMap<>();

    private ComputationPool(T prototype) {
        this.prototype = prototype;
    }
    @SuppressWarnings("unchecked")
    public static <T extends ComputationObject<T>> ComputationPool<T> getInstance(T prototype) {
        Class<?> key = prototype.getClass();
        return (ComputationPool<T>) instances.computeIfAbsent(key, k -> new ComputationPool<>(prototype));
    }

    public T acquire() {
        T obj = this.pool.poll();
        if (obj == null) {
            return this.prototype.clone();
        }
        return obj;
    }

    public void release(T obj) {
        this.pool.offer(obj);
    }

    public int size() {
        return pool.size();
    }
}
