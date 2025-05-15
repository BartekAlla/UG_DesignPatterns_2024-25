
package pl.edu.ug.computation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ComputationPool<T extends ComputationObject<T>> {
    private final ConcurrentLinkedQueue<T> pool = new ConcurrentLinkedQueue<>();
    private final T prototype;
    private final int maxSize;
    private static final Map<Class<?>, ComputationPool<?>> instances = new ConcurrentHashMap<>();

    private ComputationPool(T prototype, int maxSize) {
        this.prototype = prototype;
        this.maxSize = maxSize;
    }

    @SuppressWarnings("unchecked")
    public static <T extends ComputationObject<T>> ComputationPool<T> getInstance(T prototype, int maxSize) {
        return (ComputationPool<T>) instances.computeIfAbsent(
                prototype.getClass(),
                k -> new ComputationPool<>(prototype, maxSize)
        );
    }

    public T acquire() {
        T obj = pool.poll();
        if (obj == null) {
            return prototype.clone();
        }
        return obj;
    }

    public void release(T obj) {
        if (pool.size() < maxSize) {
            pool.offer(obj);
        }

    }

    public int size() {
        return pool.size();
    }

    public int getMaxSize() {
        return maxSize;
    }
}