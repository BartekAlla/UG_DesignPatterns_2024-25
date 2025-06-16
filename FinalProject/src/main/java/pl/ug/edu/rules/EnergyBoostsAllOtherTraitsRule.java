package pl.ug.edu.rules;

import pl.ug.edu.composite.RobotSquad;
import pl.ug.edu.traits.TraitType;

public class EnergyBoostsAllOtherTraitsRule implements EnhancementRule {
    @Override
    public boolean condition(RobotSquad squad) {
        return squad.getTraitValue(TraitType.ENERGY) > 1500;
    }

    @Override
    public void apply(RobotSquad squad) {
        for (TraitType type : TraitType.values()) {
            if (type != TraitType.ENERGY) {
                double current = squad.getTraitValue(type);
                double bonus = (current * 0.1);
                squad.modifyTrait(type, bonus);
            }
        }
    }
}
