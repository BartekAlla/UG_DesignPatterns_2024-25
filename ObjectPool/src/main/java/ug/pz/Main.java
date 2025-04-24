package ug.pz;

public class Main {
    public static void main(String[] args) {
        Pool<PooledObject> pool = new Pool<>(new PooledObjectFactory());

        PooledObject obj1 = pool.get();
        obj1.setName("First");
        show(obj1);

        PooledObject obj2 = pool.get();
        obj2.setName("Second");
        show(obj2);

        pool.release(obj1);

        PooledObject obj3 = pool.get();
        obj3.setName("Third");
        show(obj3);
    }

    private static void show(PooledObject obj) {
        System.out.println(obj.getPermanentId() + " - " + obj.getName());
    }
}