package pl.ug.edu.strategy;

import pl.ug.edu.composite.RobotSquad;
import pl.ug.edu.composite.RobotUnit;
import pl.ug.edu.composite.SingleRobot;
import pl.ug.edu.mission.Mission;
import pl.ug.edu.model.robot.Robot;

import java.util.ArrayList;
import java.util.List;

public class OptimalSelectionStrategy implements SquadSelectionStrategy {
    @Override
    public RobotUnit selectSquad(List<Robot> availableRobots, Mission mission) {
        int n = availableRobots.size();
        RobotSquad best = null;

        for (int i = 1; i < (1 << n); i++) {
            List<Robot> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(availableRobots.get(j));
                }
            }

            RobotSquad squad = new RobotSquad("OptimalSquad");
            for (Robot r : subset) {
                squad.addMember(new SingleRobot(r));
            }

            if (mission.isSatisfiedBy(squad)) {
                if (best == null || squad.getMemberCount() < best.getMemberCount()) {
                    best = squad;
                }
            }
        }

        return best;
    }
}
