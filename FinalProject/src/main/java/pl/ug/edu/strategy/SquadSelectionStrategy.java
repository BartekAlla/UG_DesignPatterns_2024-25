package pl.ug.edu.strategy;

import pl.ug.edu.composite.RobotUnit;
import pl.ug.edu.mission.Mission;
import pl.ug.edu.model.robot.Robot;

import java.util.List;

public interface SquadSelectionStrategy {
    RobotUnit selectSquad(List<Robot> availableRobots, Mission mission);
}
