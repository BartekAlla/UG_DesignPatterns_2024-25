package structural;

import org.junit.jupiter.api.Test;
import pl.ug.edu.composite.RobotSquad;
import pl.ug.edu.composite.SingleRobot;
import pl.ug.edu.model.robot.Robot;
import pl.ug.edu.model.robot.RobotBuilder;
import pl.ug.edu.traits.TraitType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompositeTest {
    @Test
    void robotSquadShouldAggregateTraitsFromMembers() {
        Robot r1 = new RobotBuilder("R1").withPower(100).build();
        Robot r2 = new RobotBuilder("R2").withPower(200).build();

        RobotSquad squad = new RobotSquad("TestSquad");
        squad.addMember(new SingleRobot(r1));
        squad.addMember(new SingleRobot(r2));

        assertEquals(300, squad.getTraitValue(TraitType.POWER), 0.001);
    }
}
