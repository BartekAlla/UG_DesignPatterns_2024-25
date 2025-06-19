package pl.ug.edu.execution;

import pl.ug.edu.composite.RobotUnit;
import pl.ug.edu.mission.Mission;
import pl.ug.edu.model.robot.Robot;
import pl.ug.edu.strategy.OptimalSelectionStrategy;

import java.util.List;

public class OptimalMissionPlan extends MissionExecutionPlan {

    @Override
    protected RobotUnit selectSquad(List<Robot> available, Mission mission) {
        return new OptimalSelectionStrategy().selectSquad(available, mission);
    }


}
