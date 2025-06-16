package pl.ug.edu.decorator;

import pl.ug.edu.composite.RobotUnit;
import pl.ug.edu.traits.TraitType;

import java.util.EnumMap;
import java.util.Map;

public class StealthDecorator extends RobotUnitDecorator {
    private final double stealthBonus;

    public StealthDecorator(RobotUnit decorated) {
        this(decorated, 100.0);
    }

    public StealthDecorator(RobotUnit decorated, double bonus) {
        super(decorated);
        this.stealthBonus = bonus;
    }

    @Override
    public Map<TraitType, Double> getAllTraitValues() {
        Map<TraitType, Double> base = new EnumMap<>(decorated.getAllTraitValues());
        base.merge(TraitType.STEALTH, stealthBonus, Double::sum);
        return base;
    }

    @Override
    public double getTraitValue(TraitType type) {
        if (type == TraitType.STEALTH) {
            return super.getTraitValue(type) + stealthBonus;
        }
        return super.getTraitValue(type);
    }

    @Override
    public String getName() {
        return decorated.getName() + " [Stealth]";
    }
}
