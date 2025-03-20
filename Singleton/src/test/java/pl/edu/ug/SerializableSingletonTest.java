package pl.edu.ug;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class SerializableSingletonTest {
    private <T> T serializeAndDeserialize(T instance, String filename) throws IOException, ClassNotFoundException {

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(instance);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        T deserializedInstance = (T) in.readObject();
        in.close();

        return deserializedInstance;
    }


    @Test
    void testSingletonNotPassingSerialization() throws Exception {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = serializeAndDeserialize(instance1, "singleton.txt");

        System.out.println("Singletons hashcodes:");
        System.out.println("Instance 1: " + instance1.hashCode());
        System.out.println("Instance 2: " + instance2.hashCode());

        assertNotSame(instance1, instance2);
    }


    @Test
    void testSerializableSingletonPassesSerialization() throws Exception {
        SerializableSingleton instance1 = SerializableSingleton.getInstance();
        SerializableSingleton instance2 = serializeAndDeserialize(instance1, "serializableSingleton.txt");

        System.out.println("Serializable Singleton hashcodes:");
        System.out.println("Instance 1: " + instance1.hashCode());
        System.out.println("Instance 2: " + instance2.hashCode());

        assertSame(instance1, instance2);
    }

}