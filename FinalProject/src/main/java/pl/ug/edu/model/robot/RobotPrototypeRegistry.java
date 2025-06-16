package pl.ug.edu.model.robot;

import java.util.HashMap;
import java.util.Map;

public class RobotPrototypeRegistry {
    private final Map<String, Robot> registry = new HashMap<>();

    public void registerPrototype(String key, Robot robot) {
        registry.put(key, robot);
    }

    public Robot createClone(String key) {
        Robot prototype = registry.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("No prototype found for key: " + key);
        }
        return prototype.copy();
    }

    public boolean hasPrototype(String key) {
        return registry.containsKey(key);
    }
}
