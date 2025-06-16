package pl.ug.edu;

import pl.ug.edu.composite.RobotSquad;
import pl.ug.edu.composite.SingleRobot;
import pl.ug.edu.model.robot.Robot;
import pl.ug.edu.model.robot.RobotBuilder;
import pl.ug.edu.model.robot.RobotPrototypeRegistry;
import pl.ug.edu.rules.ArmorBoostsEnergyRule;
import pl.ug.edu.rules.IntelligenceBoostsEnergyRule;
import pl.ug.edu.rules.StrengthBoostsArmorRule;
import pl.ug.edu.rules.TeamSizeBoostsPowerRule;

public class Main {
    public static void main(String[] args) {

//        RobotPrototypeRegistry registry = new RobotPrototypeRegistry();
//
//        Robot assaultBot = new RobotBuilder("Assault-X1")
//                .withPower(80)
//                .withArmor(60)
//                .withEnergy(60)
//                .withIntelligence(20)
//                .build();
//        System.out.println(assaultBot);
//        registry.registerPrototype("assault", assaultBot);
//        Robot clonedAssaultBot = registry.createClone("assault");
//        System.out.println(clonedAssaultBot);
        RobotPrototypeRegistry registry = new RobotPrototypeRegistry();

        Robot scout = new RobotBuilder("Scout-X")
                .withPower(40)
                .withArmor(30)
                .withEnergy(50)
                .withIntelligence(300)
                .build();
        registry.registerPrototype("scout", scout);

        Robot brute = new RobotBuilder("Brute-Z")
                .withPower(250)
                .withArmor(100)
                .withEnergy(100)
                .withIntelligence(50)
                .build();
        registry.registerPrototype("brute", brute);


        RobotSquad reconSquad = new RobotSquad("ReconSquad");
        for (int i = 0; i <= 4; i++) {
            reconSquad.addMember(new SingleRobot(registry.createClone("scout")));
        }

        RobotSquad assaultSquad = new RobotSquad("AssaultSquad");
        for (int i = 0; i < 3; i++) {
            assaultSquad.addMember(new SingleRobot(registry.createClone("brute")));
        }

        // === Dodanie reguł ===
        reconSquad.addRule(new IntelligenceBoostsEnergyRule());      // 🧠 > 1000 => ⚡ +20%
        reconSquad.addRule(new TeamSizeBoostsPowerRule());         // 👥 > 5 => 💪 +100
        assaultSquad.addRule(new StrengthBoostsArmorRule());           // 💪 > 800 => 🛡️ +50
        assaultSquad.addRule(new ArmorBoostsEnergyRule());          // 🛡️ > 1000 => ⚡ +200

        // === Statystyki przed ===
        System.out.println("=== BEFORE RULES ===");
        printSquadStats(reconSquad);
        printSquadStats(assaultSquad);

        // === Zastosowanie reguł ===
        reconSquad.evaluateRules();
        assaultSquad.evaluateRules();

        // === Statystyki po ===
        System.out.println("\n=== AFTER RULES ===");
        printSquadStats(reconSquad);
        printSquadStats(assaultSquad);
    }

    private static void printSquadStats(RobotSquad squad) {
        System.out.println("-> Squad: " + squad.getName());
        squad.getAllTraitValues().forEach((type, value) ->
                System.out.println("   " + type + ": " + value));
    }
}
