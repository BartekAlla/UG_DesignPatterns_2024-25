package pl.ug.edu.strategy;

import pl.ug.edu.composite.RobotSquad;
import pl.ug.edu.composite.RobotUnit;
import pl.ug.edu.composite.SingleRobot;
import pl.ug.edu.decorator.ExtraPowerDecorator;
import pl.ug.edu.decorator.RobotUnitDecorator;
import pl.ug.edu.decorator.ShieldDecorator;
import pl.ug.edu.decorator.StealthDecorator;
import pl.ug.edu.mission.Mission;
import pl.ug.edu.model.robot.Robot;
import pl.ug.edu.traits.TraitType;

import java.util.List;

public class OptimalSelectionStrategy implements SquadSelectionStrategy {

    @Override
    public RobotUnit selectSquad(List<Robot> availableRobots, Mission mission) {
        int n = availableRobots.size();
        RobotUnit best = null;

        for (int i = 1; i < (1 << n); i++) {
            RobotSquad squad = new RobotSquad("OptimalSquad");

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    squad.addMember(new SingleRobot(availableRobots.get(j)));
                }
            }

            RobotUnit decorated = applyDecorators(squad);

            if (mission.isSatisfiedBy(decorated)) {
                if (best == null || getMemberCount(decorated) < getMemberCount(best)) {
                    best = decorated;
                }
            }
        }

        return best;
    }

    private RobotUnit applyDecorators(RobotUnit squad) {
        RobotUnit result = squad;

        if (squad.getTraitValue(TraitType.INTELLIGENCE) > 1000) {
            result = new StealthDecorator(result);
        }
        if (squad.getTraitValue(TraitType.ARMOR) > 800) {
            result = new ShieldDecorator(result);
        }
        if (squad.getTraitValue(TraitType.POWER) > 1500) {
            result = new ExtraPowerDecorator(result);
        }

        return result;
    }

    private int getMemberCount(RobotUnit unit) {
        if (unit instanceof RobotSquad squad) return squad.getMemberCount();
        if (unit instanceof RobotUnitDecorator decorator) return getMemberCount(decorator.getDecorated());
        return 1;
    }
}
