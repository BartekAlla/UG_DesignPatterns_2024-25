package ug.pz;

import java.util.ArrayList;
import java.util.List;

public class Pool<T> {
    private final List<T> available = new ArrayList<>();
    private final List<T> inUse = new ArrayList<>();
    private final ObjectFactory<T> factory;

    public Pool(ObjectFactory<T> factory) {
        this.factory = factory;
    }

    public synchronized T get() {
        if (!available.isEmpty()) {
            T obj = available.remove(0);
            inUse.add(obj);
            return obj;
        } else {
            T obj = factory.createObject();
            inUse.add(obj);
            return obj;
        }
    }

    public synchronized void release(T obj) {
        cleanUp(obj);
        inUse.remove(obj);
        available.add(obj);
    }

    protected void cleanUp(T obj) {
        // Czyszczenie, jeśli potrzebne
    }
}
