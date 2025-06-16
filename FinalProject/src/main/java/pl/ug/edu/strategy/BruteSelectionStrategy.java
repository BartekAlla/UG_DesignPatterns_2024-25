package pl.ug.edu.strategy;

import pl.ug.edu.composite.RobotSquad;
import pl.ug.edu.composite.RobotUnit;
import pl.ug.edu.composite.SingleRobot;
import pl.ug.edu.mission.Mission;
import pl.ug.edu.model.robot.Robot;

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
            if (mission.isSatisfiedBy(squad)) {
                return squad;
            }
        }

        return null;
    }
}
