package behavioral;

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

public class TemplateMethodTest {
    @Test
    void missionPlanShouldReturnValidDecoratedSquad() {
        List<Robot> robots = List.of(
                new RobotBuilder("IntelliBot").withIntelligence(2200).build(),
                new RobotBuilder("Tank").withArmor(900).build()
        );

        Mission mission = new Mission("Intel Mission", Map.of(
                TraitType.INTELLIGENCE, 1000.0,
                TraitType.ARMOR, 800.0
        ));

        MissionExecutionPlan plan = new BruteMissionPlan();
        RobotUnit[] result = plan.execute(robots, mission);

        assertNotNull(result);
        assertTrue(result[1].getTraitValue(TraitType.ARMOR) > result[0].getTraitValue(TraitType.ARMOR));
        assertEquals(1.0, result[1].getTraitValue(TraitType.STEALTH), 0.001);
    }
}
