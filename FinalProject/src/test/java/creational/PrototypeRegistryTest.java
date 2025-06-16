package creational;

import org.junit.jupiter.api.Test;
import pl.ug.edu.model.robot.Robot;
import pl.ug.edu.model.robot.RobotBuilder;
import pl.ug.edu.model.robot.RobotPrototypeRegistry;
import pl.ug.edu.traits.TraitType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PrototypeRegistryTest {
    @Test
    void prototypeShouldCreateIndependentClones() {
        RobotBuilder builder = new RobotBuilder("Proto");
        Robot proto = builder.withPower(100).build();

        RobotPrototypeRegistry registry = new RobotPrototypeRegistry();
        registry.registerPrototype("test", proto);

        Robot clone1 = registry.createClone("test");
        Robot clone2 = registry.createClone("test");

        assertNotEquals(clone1.getName(), clone2.getName());
        assertEquals(100, clone1.getTrait(TraitType.POWER).getValue(), 0.001);
    }
}
