package pl.ug.edu.execution;

import pl.ug.edu.composite.RobotSquad;
import pl.ug.edu.composite.RobotUnit;
import pl.ug.edu.composite.SingleRobot;
import pl.ug.edu.decorator.RobotUnitDecorator;
import pl.ug.edu.mission.Mission;
import pl.ug.edu.model.robot.Robot;

import java.util.List;

public abstract class MissionExecutionPlan {

    public final RobotUnit[] execute(List<Robot> available, Mission mission) {
        RobotUnit decoratedSquad = selectSquad(available, mission);

        if (decoratedSquad == null) {
            System.out.println("No squad can execute mission.");
            return null;
        }

        RobotUnit base = decoratedSquad instanceof RobotUnitDecorator d
                ? d.getDecorated()
                : decoratedSquad;

        return new RobotUnit[]{base, decoratedSquad};
    }

    protected abstract RobotUnit selectSquad(List<Robot> available, Mission mission);


    public static void printSquadSummary(String title, RobotUnit base, RobotUnit decorated) {
        System.out.println("=== " + title + " ===\n");

        if (decorated == null) {
            System.out.println("Mission could not be completed.\n");
            return;
        }
        System.out.println("Successfully selected squad for mission.\n");
        System.out.println("Squad selected: " + decorated.getName());

        System.out.println("\nBase Traits:");
        base.getAllTraitValues().forEach((k, v) -> {
            System.out.println(" - " + k + ": " + v);
        });

        System.out.println("\nFinal Traits:");
        decorated.getAllTraitValues().forEach((k, v) -> {
            System.out.println(" - " + k + ": " + v);
        });

        System.out.println("\nMembers:");
        printMembersRecursive(decorated);
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
