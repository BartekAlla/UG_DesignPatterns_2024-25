package pl.ug.edu.execution;

import pl.ug.edu.composite.RobotUnit;
import pl.ug.edu.decorator.ExtraPowerDecorator;
import pl.ug.edu.decorator.ShieldDecorator;
import pl.ug.edu.decorator.StealthDecorator;
import pl.ug.edu.mission.Mission;
import pl.ug.edu.model.robot.Robot;
import pl.ug.edu.strategy.BruteSelectionStrategy;
import pl.ug.edu.traits.TraitType;

import java.util.List;

public class BruteMissionPlan extends MissionExecutionPlan {

    @Override
    protected RobotUnit selectSquad(List<Robot> available, Mission mission) {
        return new BruteSelectionStrategy().selectSquad(available, mission);
    }

    @Override
    protected RobotUnit applyDecorators(RobotUnit squad) {
        RobotUnit result = squad;

        if (squad.getTraitValue(TraitType.INTELLIGENCE) > 2000) {
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
}
