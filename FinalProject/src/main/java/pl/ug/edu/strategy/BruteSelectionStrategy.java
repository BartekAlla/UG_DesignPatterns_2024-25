package pl.ug.edu.strategy;

import pl.ug.edu.composite.RobotSquad;
import pl.ug.edu.composite.RobotUnit;
import pl.ug.edu.composite.SingleRobot;
import pl.ug.edu.decorator.ExtraPowerDecorator;
import pl.ug.edu.decorator.ShieldDecorator;
import pl.ug.edu.decorator.StealthDecorator;
import pl.ug.edu.mission.Mission;
import pl.ug.edu.model.robot.Robot;
import pl.ug.edu.traits.TraitType;

import java.util.List;

public class BruteSelectionStrategy implements SquadSelectionStrategy {

    @Override
    public RobotUnit selectSquad(List<Robot> availableRobots, Mission mission) {
        int n = availableRobots.size();

        for (int i = 1; i < (1 << n); i++) {
            RobotSquad squad = new RobotSquad("BruteSquad");

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    squad.addMember(new SingleRobot(availableRobots.get(j)));
                }
            }

            RobotUnit decorated = applyDecorators(squad);

            if (mission.isSatisfiedBy(decorated)) {
                return decorated;
            }
        }

        return null;
    }

    private RobotUnit applyDecorators(RobotUnit squad) {
        RobotUnit result = squad;

        double intelligence = squad.getTraitValue(TraitType.INTELLIGENCE);
        double power = squad.getTraitValue(TraitType.POWER);
        double armor = squad.getTraitValue(TraitType.ARMOR);

        if (intelligence > 1000) {
            result = new StealthDecorator(result);
        }

        if (armor > 800) {
            result = new ShieldDecorator(result);
        }

        if (power > 1500) {
            result = new ExtraPowerDecorator(result);
        }

        return result;
    }
}
