package pl.ug.edu.decorator;

import pl.ug.edu.composite.RobotUnit;
import pl.ug.edu.traits.TraitType;

import java.util.EnumMap;
import java.util.Map;

public class ShieldDecorator extends RobotUnitDecorator {
    private final double extraArmor = 150.0;

    public ShieldDecorator(RobotUnit decorated) {
        super(decorated);
    }

    @Override
    public Map<TraitType, Double> getAllTraitValues() {
        Map<TraitType, Double> base = new EnumMap<>(decorated.getAllTraitValues());
        base.merge(TraitType.ARMOR, extraArmor, Double::sum);
        return base;
    }

    @Override
    public double getTraitValue(TraitType type) {
        if (type == TraitType.ARMOR) {
            return super.getTraitValue(type) + extraArmor;
        }
        return super.getTraitValue(type);
    }

    @Override
    public String getName() {
        return decorated.getName() + " [Armor Boost]";
    }
}
