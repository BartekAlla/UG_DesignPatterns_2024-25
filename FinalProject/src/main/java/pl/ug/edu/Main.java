package pl.ug.edu;

import pl.ug.edu.composite.RobotUnit;
import pl.ug.edu.execution.BruteMissionPlan;
import pl.ug.edu.execution.MissionExecutionPlan;
import pl.ug.edu.execution.OptimalMissionPlan;
import pl.ug.edu.mission.Mission;
import pl.ug.edu.model.robot.Robot;
import pl.ug.edu.model.robot.RobotBuilder;
import pl.ug.edu.model.robot.RobotPrototypeRegistry;
import pl.ug.edu.traits.TraitType;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static pl.ug.edu.execution.MissionExecutionPlan.printSquadSummary;

public class Main {
    public static void main(String[] args) {
        RobotBuilder assaultBuilder = new RobotBuilder("Assault");
        RobotBuilder scoutBuilder = new RobotBuilder("Scout");
        RobotBuilder heavyBuilder = new RobotBuilder("Heavy");

        Robot assaultProto = assaultBuilder
                .withPower(300)
                .withArmor(200)
                .withEnergy(150)
                .withIntelligence(100)
                .build();

        Robot scoutProto = scoutBuilder
                .withPower(100)
                .withArmor(80)
                .withEnergy(300)
                .withIntelligence(400)
                .build();

        Robot heavyProto = heavyBuilder
                .withPower(500)
                .withArmor(600)
                .withEnergy(200)
                .withIntelligence(150)
                .build();

        RobotPrototypeRegistry registry = new RobotPrototypeRegistry();
        registry.registerPrototype("assault", assaultProto);
        registry.registerPrototype("scout", scoutProto);
        registry.registerPrototype("heavy", heavyProto);

        List<Robot> robots = new ArrayList<>();
        robots.add(registry.createClone("assault"));
        robots.add(registry.createClone("assault"));
        robots.add(registry.createClone("assault"));
        robots.add(registry.createClone("heavy"));
        robots.add(registry.createClone("heavy"));
        robots.add(registry.createClone("scout"));
        robots.add(registry.createClone("assault"));
        robots.add(registry.createClone("heavy"));
        robots.add(registry.createClone("scout"));
        robots.add(registry.createClone("scout"));
        robots.add(registry.createClone("scout"));
        robots.add(registry.createClone("assault"));
        robots.add(registry.createClone("heavy"));
        robots.add(registry.createClone("assault"));
        robots.add(registry.createClone("scout"));
        robots.add(registry.createClone("heavy"));


        System.out.println("=== Available Robots ===");
        for (Robot r : robots) {
            System.out.println("- " + r.getName());
            r.getTraits().forEach((type, trait) ->
                    System.out.println("    " + type + ": " + trait.getValue()));
        }

        System.out.println();
        Map<TraitType, Double> missionRequirements = new EnumMap<>(TraitType.class);
        missionRequirements.put(TraitType.POWER, 1200.0);
        missionRequirements.put(TraitType.ARMOR, 1000.0);
        missionRequirements.put(TraitType.ENERGY, 800.0);
        missionRequirements.put(TraitType.INTELLIGENCE, 1000.0);
        missionRequirements.put(TraitType.STEALTH, 1.0);

        Mission destroyBase = new Mission("Destroy Reactor Core", missionRequirements);

        System.out.println("=== Mission Requirements ===");
        System.out.println(destroyBase);
        System.out.println();

        MissionExecutionPlan brutePlan = new BruteMissionPlan();
        RobotUnit[] bruteResult = brutePlan.execute(robots, destroyBase);
        if (bruteResult != null) {
            printSquadSummary("BruteMissionPlan", bruteResult[0], bruteResult[1]);
        }

        MissionExecutionPlan optimalPlan = new OptimalMissionPlan();
        RobotUnit[] optimalResult = optimalPlan.execute(robots, destroyBase);
        if (optimalResult != null) {
            printSquadSummary("OptimalMissionPlan", optimalResult[0], optimalResult[1]);
        }
    }
}
