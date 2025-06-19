package structural;

import org.junit.jupiter.api.Test;
import pl.ug.edu.composite.RobotSquad;
import pl.ug.edu.composite.RobotUnit;
import pl.ug.edu.composite.SingleRobot;
import pl.ug.edu.decorator.ExtraPowerDecorator;
import pl.ug.edu.decorator.ShieldDecorator;
import pl.ug.edu.decorator.StealthDecorator;
import pl.ug.edu.model.robot.Robot;
import pl.ug.edu.model.robot.RobotBuilder;
import pl.ug.edu.traits.TraitType;

import static org.junit.jupiter.api.Assertions.*;

public class DecoratorEffectTest {
    @Test
    void stealthTraitShouldBeAdded() {
        Robot r = new RobotBuilder("Spy").withIntelligence(1200).build();
        RobotSquad squad = new RobotSquad("SpySquad");
        squad.addMember(new SingleRobot(r));

        assertFalse(squad.getAllTraitValues().containsKey(TraitType.STEALTH));

        RobotUnit decorated = new StealthDecorator(squad);


        assertTrue(decorated.getAllTraitValues().containsKey(TraitType.STEALTH));
        assertEquals(1.0, decorated.getTraitValue(TraitType.STEALTH), 0.001);
    }

    @Test
    void shieldBoostShouldIncreaseArmor() {
        Robot r = new RobotBuilder("Tank").withArmor(900).build();
        RobotSquad squad = new RobotSquad("TankSquad");
        squad.addMember(new SingleRobot(r));

        double base = squad.getTraitValue(TraitType.ARMOR);
        RobotUnit decorated = new ShieldDecorator(squad);
        double boosted = decorated.getTraitValue(TraitType.ARMOR);

        assertTrue(boosted > base);
    }

    @Test
    void orbitalStrikeShouldAddStrikeCapability() {
        RobotSquad squad = new RobotSquad("OrbitalSquad");
        squad.addMember(new SingleRobot(new RobotBuilder("Heavy").withPower(1600).build()));

        double base = squad.getTraitValue(TraitType.POWER);
        RobotUnit decorated = new ExtraPowerDecorator(squad);
        double boosted = decorated.getTraitValue(TraitType.POWER);

        assertTrue(boosted > base);
    }
}
