package pl.ug.edu.model.robot;

import pl.ug.edu.traits.Armor;
import pl.ug.edu.traits.Energy;
import pl.ug.edu.traits.Intelligence;
import pl.ug.edu.traits.Power;

public class RobotBuilder {
    private final Robot robot;

    public RobotBuilder(String name) {
        this.robot = new Robot(name);
    }

    public RobotBuilder withPower(double value) {
        robot.addTrait(new Power(value));
        return this;
    }

    public RobotBuilder withArmor(double value) {
        robot.addTrait(new Armor(value));
        return this;
    }

    public RobotBuilder withIntelligence(double value) {
        robot.addTrait(new Intelligence(value));
        return this;
    }

    public RobotBuilder withEnergy(double value) {
        robot.addTrait(new Energy(value));
        return this;
    }

    public Robot build() {
        return robot;
    }
}
