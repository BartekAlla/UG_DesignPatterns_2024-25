package pl.ug.edu.composite;

import pl.ug.edu.model.robot.Robot;
import pl.ug.edu.traits.Trait;
import pl.ug.edu.traits.TraitType;

import java.util.EnumMap;
import java.util.Map;

public class SingleRobot implements RobotUnit{
    private final Robot robot;

    public SingleRobot(Robot robot) {
        this.robot = robot;
    }

    @Override
    public double getTraitValue(TraitType traitType) {
        Trait trait = robot.getTrait(traitType);
        return trait != null ? trait.getValue() : 0;
    }
    @Override
    public Map<TraitType, Double> getAllTraitValues() {
        Map<TraitType, Double> result = new EnumMap<>(TraitType.class);
        for (Map.Entry<TraitType, Trait> entry : robot.getTraits().entrySet()) {
            result.put(entry.getKey(), entry.getValue().getValue());
        }
        return result;
    }

    @Override
    public String getName() {
        return robot.getName();
    }
}
