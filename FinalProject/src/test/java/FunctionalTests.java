import org.junit.jupiter.api.Test;
import pl.ug.edu.composite.RobotUnit;
import pl.ug.edu.execution.BruteMissionPlan;
import pl.ug.edu.execution.MissionExecutionPlan;
import pl.ug.edu.mission.Mission;
import pl.ug.edu.model.robot.Robot;
import pl.ug.edu.model.robot.RobotBuilder;
import pl.ug.edu.traits.TraitType;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionalTests {
    @Test
    public void missionPlanShouldNotBeAccomplished() {
        List<Robot> robots = List.of(
                new RobotBuilder("Weak1").withPower(100).build(),
                new RobotBuilder("Weak2").withArmor(100).build()
        );

        Mission mission = new Mission("Impossible Mission", Map.of(
                TraitType.POWER, 500.0,
                TraitType.ARMOR, 800.0
        ));

        MissionExecutionPlan plan = new BruteMissionPlan();
        RobotUnit[] result = plan.execute(robots, mission);

        assertNull(result);
    }

    @Test
    public void missionShouldPassOnlyWithDecorator() {
        List<Robot> robots = List.of(
                new RobotBuilder("PowerBot").withPower(1000).build(),
                new RobotBuilder("Support").withPower(505).build()
        );

        Mission mission = new Mission("Powerful Strike", Map.of(
                TraitType.POWER, 1600.0
        ));

        MissionExecutionPlan plan = new BruteMissionPlan();
        RobotUnit[] result = plan.execute(robots, mission);

        assertNotNull(result, "Expected a valid squad via decorator");

        double basePower = result[0].getTraitValue(TraitType.POWER);
        double decoratedPower = result[1].getTraitValue(TraitType.POWER);

        assertTrue(decoratedPower > basePower);
        assertTrue(decoratedPower >= 1505.0);
    }

    @Test
    public void singleRobotCanPassMission() {
        Robot solo = new RobotBuilder("SuperBot")
                .withPower(1000)
                .withArmor(1000)
                .withIntelligence(1000)
                .withEnergy(1000)
                .build();

        Mission mission = new Mission("Solo Mission", Map.of(
                TraitType.POWER, 800.0,
                TraitType.ARMOR, 800.0,
                TraitType.ENERGY, 500.0
        ));

        MissionExecutionPlan plan = new BruteMissionPlan();
        RobotUnit[] result = plan.execute(List.of(solo), mission);

        assertNotNull(result);
        assertEquals("BruteSquad", result[0].getName());
    }
}
