package pl.ug.edu.decorator;

import pl.ug.edu.composite.RobotUnit;
import pl.ug.edu.traits.TraitType;

import java.util.Map;

public abstract class RobotUnitDecorator implements RobotUnit {
    protected final RobotUnit decorated;

    public RobotUnitDecorator(RobotUnit decorated) {
        this.decorated = decorated;
    }

    @Override
    public double getTraitValue(TraitType type) {
        return decorated.getTraitValue(type);
    }

    @Override
    public Map<TraitType, Double> getAllTraitValues() {
        return decorated.getAllTraitValues();
    }

    @Override
    public String getName() {
        return decorated.getName();
    }

    public RobotUnit getDecorated() {
        return decorated;
    }
}
