package pl.ug.edu.execution;

import pl.ug.edu.composite.RobotSquad;
import pl.ug.edu.composite.RobotUnit;
import pl.ug.edu.composite.SingleRobot;
import pl.ug.edu.decorator.RobotUnitDecorator;
import pl.ug.edu.mission.Mission;
import pl.ug.edu.model.robot.Robot;

import java.util.ArrayList;
import java.util.List;

public abstract class MissionExecutionPlan {
    public final RobotUnit execute(List<Robot> available, Mission mission) {
//        List<Robot> selected = selectRobots(available, mission);
//        if (selected == null || selected.isEmpty()) {
//            System.out.println("Empty robot list, cannot execute mission.");
//            return null;
//        }
//        RobotSquad squad;
//        if (this instanceof BruteMissionPlan) {
//            squad = new RobotSquad("BruteSquad");
//        } else {
//            squad = new RobotSquad("OptimalSquad");
//        }
//        for (Robot r : selected) {
//            squad.addMember(new SingleRobot(r));
//        }
//
//        RobotUnit evaluatedSquad = applyDecorators(squad);
//
//
//        if (mission.isSatisfiedBy(evaluatedSquad)) {
//            return evaluatedSquad;
//        } else {
//            return null;
//        }
        RobotUnit squad = selectSquad(available, mission);
        if (squad == null) {
            System.out.println("No squad can execute mission.");
            return null;
        }

        RobotUnit decorated = applyDecorators(squad);

        if (mission.isSatisfiedBy(decorated)) {
            return decorated;
        } else {
            return null;
        }
    }


    //    protected abstract List<Robot> selectRobots(List<Robot> available, Mission mission);
    protected abstract RobotUnit selectSquad(List<Robot> available, Mission mission);

    //    protected RobotUnit applyDecorators(RobotSquad squad) {
    protected RobotUnit applyDecorators(RobotUnit squad) {
        return squad;
    }

//    protected List<Robot> extractRobotsFromUnit(RobotUnit unit) {
//        List<Robot> robots = new ArrayList<>();
//
//        if (unit instanceof SingleRobot sr) {
//            robots.add(sr.getRobot());
//        } else if (unit instanceof RobotSquad squad) {
//            for (RobotUnit member : squad.getMembers()) {
//                robots.addAll(extractRobotsFromUnit(member));
//            }
//        }
//
//        return robots;
//    }

    public static void printSquadSummary(String title, RobotUnit unit) {
        System.out.println("=== " + title + " ===\n");

        if (unit == null) {
            System.out.println("Mission could not be completed.\n");
            return;
        }

        System.out.println("Squad selected: " + unit.getName());

        System.out.println("\nBase Traits:");
        unit.getAllTraitValues().forEach((k, v) -> {
            System.out.println(" - " + k + ": " + v);
        });
        System.out.println();

        System.out.println("Final Traits:");
        unit.getAllTraitValues().forEach((k, v) -> {
            System.out.println(" - " + k + ": " + v);
        });

        System.out.println("\nMembers:");
        if (unit instanceof RobotUnitDecorator decorated) {
            printMembersRecursive(decorated.getDecorated());
        } else {
            printMembersRecursive(unit);
        }

        System.out.println();
    }

    private static void printMembersRecursive(RobotUnit unit) {
        if (unit instanceof SingleRobot single) {
            System.out.println(" - " + single.getName());
        } else if (unit instanceof RobotSquad squad) {
            for (RobotUnit member : squad.getMembers()) {
                printMembersRecursive(member);
            }
        } else if (unit instanceof RobotUnitDecorator decorated) {
            printMembersRecursive(decorated.getDecorated());
        }
    }
}
