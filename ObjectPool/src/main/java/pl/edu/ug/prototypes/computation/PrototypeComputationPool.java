
package pl.edu.ug.prototypes.computation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class PrototypeComputationPool<T extends PrototypeComputationObject<T>> {
    private final ConcurrentLinkedQueue<T> pool = new ConcurrentLinkedQueue<>();
    private final T prototype;
    private final int maxSize;
    private static final Map<Class<?>, PrototypeComputationPool<?>> instances = new ConcurrentHashMap<>();
    private final AtomicInteger createdObjects = new AtomicInteger();

    private PrototypeComputationPool(T prototype, int maxSize) {
        this.prototype = prototype;
        this.maxSize = maxSize;
    }

    @SuppressWarnings("unchecked")
    public static <T extends PrototypeComputationObject<T>> PrototypeComputationPool<T> getInstance(T prototype, int maxSize) {
        return (PrototypeComputationPool<T>) instances.computeIfAbsent(
                prototype.getClass(),
                k -> new PrototypeComputationPool<>(prototype, maxSize)
        );
    }

    public T acquire() {
        T obj = pool.poll();
        if (obj == null) {
            createdObjects.incrementAndGet();
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

    public int getCreatedObjectsCount() {
        return createdObjects.get();
    }

    public static void resetAll() {
        for (PrototypeComputationPool<?> pool : instances.values()) {
            pool.reset();
        }
        instances.clear();
    }

    private void reset() {
        pool.clear();
        createdObjects.set(0);
    }
}