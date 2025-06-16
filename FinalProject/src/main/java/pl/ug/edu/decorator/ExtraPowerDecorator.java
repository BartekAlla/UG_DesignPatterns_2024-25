package pl.ug.edu.decorator;

import pl.ug.edu.composite.RobotUnit;
import pl.ug.edu.traits.TraitType;

import java.util.EnumMap;
import java.util.Map;

public class ExtraPowerDecorator extends RobotUnitDecorator {
    private final double extraPower = 300.0;

    public ExtraPowerDecorator(RobotUnit decorated) {
        super(decorated);
    }

    @Override
    public Map<TraitType, Double> getAllTraitValues() {
        Map<TraitType, Double> base = new EnumMap<>(decorated.getAllTraitValues());
        base.merge(TraitType.POWER, extraPower, Double::sum);
        return base;
    }

    @Override
    public double getTraitValue(TraitType type) {
        if (type == TraitType.POWER) {
            return super.getTraitValue(type) + extraPower;
        }
        return super.getTraitValue(type);
    }

    @Override
    public String getName() {
        return decorated.getName() + " [Extra Power]";
    }
}
