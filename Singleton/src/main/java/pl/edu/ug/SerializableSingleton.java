package pl.edu.ug;

import java.io.Serializable;

public class SerializableSingleton implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final SerializableSingleton uniqueInstance = new SerializableSingleton();

    private SerializableSingleton() {
    }

    public static SerializableSingleton getInstance() {
        return uniqueInstance;
    }

    protected Object readResolve() {
        return uniqueInstance;
    }
}
