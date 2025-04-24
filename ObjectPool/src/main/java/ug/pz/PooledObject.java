package ug.pz;

public class PooledObject {
    private static int nextId = 1;
    private static final Object idLock = new Object();

    private final int permanentId;
    private String name;

    public PooledObject() {
        synchronized (idLock) {
            this.permanentId = nextId++;
        }
    }

    public int getPermanentId() {
        return permanentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
