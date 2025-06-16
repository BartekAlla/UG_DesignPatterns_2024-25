package creational;

import org.junit.jupiter.api.Test;
import pl.ug.edu.model.robot.Robot;
import pl.ug.edu.model.robot.RobotBuilder;
import pl.ug.edu.traits.TraitType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuilderTest {
    @Test
    void builderShouldBuildRobotWithCorrectTraits() {
        Robot robot = new RobotBuilder("TestBot")
                .withPower(100)
                .withArmor(200)
                .withEnergy(300)
                .withIntelligence(400)
                .build();

        assertEquals(100, robot.getTrait(TraitType.POWER).getValue(), 0.001);
        assertEquals(200, robot.getTrait(TraitType.ARMOR).getValue(), 0.001);
        assertEquals(300, robot.getTrait(TraitType.ENERGY).getValue(), 0.001);
        assertEquals(400, robot.getTrait(TraitType.INTELLIGENCE).getValue(), 0.001);
    }
}
