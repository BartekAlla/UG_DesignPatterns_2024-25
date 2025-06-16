package behavioral;

import org.junit.jupiter.api.Test;
import pl.ug.edu.composite.RobotSquad;
import pl.ug.edu.composite.RobotUnit;
import pl.ug.edu.mission.Mission;
import pl.ug.edu.model.robot.Robot;
import pl.ug.edu.model.robot.RobotBuilder;
import pl.ug.edu.strategy.BruteSelectionStrategy;
import pl.ug.edu.strategy.OptimalSelectionStrategy;
import pl.ug.edu.traits.TraitType;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StrategyTest {
    @Test
    void optimalShouldReturnSmallerSquadThanBrute() {
        List<Robot> robots = List.of(
                new RobotBuilder("A").withPower(100).build(),
                new RobotBuilder("B").withPower(100).build(),
                new RobotBuilder("C").withPower(300).build(),
                new RobotBuilder("D").withPower(300).build()
        );

        Mission mission = new Mission("Test", Map.of(TraitType.POWER, 600.0));

        RobotUnit brute = new BruteSelectionStrategy().selectSquad(robots, mission);
        RobotUnit optimal = new OptimalSelectionStrategy().selectSquad(robots, mission);

        assertTrue(optimal.getAllTraitValues().get(TraitType.POWER) >= 600);
        assertTrue(optimal instanceof RobotSquad);
        assertTrue(((RobotSquad) optimal).getMemberCount() <= ((RobotSquad) brute).getMemberCount());
    }
}
