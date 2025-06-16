package pl.ug.edu;

import pl.ug.edu.model.robot.Robot;
import pl.ug.edu.model.robot.RobotBuilder;
import pl.ug.edu.model.robot.RobotPrototypeRegistry;

public class Main {
    public static void main(String[] args) {

        RobotPrototypeRegistry registry = new RobotPrototypeRegistry();

        Robot assaultBot = new RobotBuilder("Assault-X1")
                .withPower(80)
                .withArmor(60)
                .withEnergy(60)
                .withIntelligence(20)
                .build();
        System.out.println(assaultBot);
        registry.registerPrototype("assault", assaultBot);
        Robot clonedAssaultBot = registry.createClone("assault");
        System.out.println(clonedAssaultBot);
    }
}