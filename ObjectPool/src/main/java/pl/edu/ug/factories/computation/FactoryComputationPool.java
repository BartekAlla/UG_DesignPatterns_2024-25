
package pl.edu.ug.factories.computation;

import pl.edu.ug.prototypes.computation.PrototypeComputationObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;


public class FactoryComputationPool<T extends PrototypeComputationObject<T>> {
    private final ConcurrentLinkedQueue<T> pool = new ConcurrentLinkedQueue<>();
    private static final Map<Class<?>, FactoryComputationPool<?>> instances = new ConcurrentHashMap<>();
    private final FactoryComputationObject<T> factory;
    private final AtomicInteger createdObjects = new AtomicInteger();

    private FactoryComputationPool(FactoryComputationObject<T> factory) {
        this.factory = factory;
    }

    @SuppressWarnings("unchecked")
    public static <T extends PrototypeComputationObject<T>> FactoryComputationPool<T> getInstance(FactoryComputationObject<T> factory) {
        Class<?> key = factory.getClass();
        return (FactoryComputationPool<T>) instances.computeIfAbsent(key, k -> new FactoryComputationPool<>(factory));
    }

    public T getObject() {
        T obj = pool.poll();
        if (obj == null) {
            createdObjects.incrementAndGet();
            return this.factory.createComputationObject();
        }
        return obj;
    }

    public void releaseObject(T obj) {
      this.pool.offer(obj);

    }

    public int size() {
        return this.pool.size();
    }
    public int getCreatedObjectsCount() {
        return createdObjects.get();
    }
    public static void resetAll() {
        instances.clear();
    }
}