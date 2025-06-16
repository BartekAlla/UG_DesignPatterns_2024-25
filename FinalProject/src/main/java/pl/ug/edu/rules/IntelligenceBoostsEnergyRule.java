package pl.ug.edu.rules;

import pl.ug.edu.composite.RobotSquad;
import pl.ug.edu.traits.TraitType;

public class IntelligenceBoostsEnergyRule implements EnhancementRule {
    @Override
    public boolean condition(RobotSquad squad) {
        return squad.getTraitValue(TraitType.INTELLIGENCE) > 1000;
    }

    @Override
    public void apply(RobotSquad squad) {
        double current = squad.getTraitValue(TraitType.ENERGY);
        double bonus = (current * 0.2);
        squad.modifyTrait(TraitType.ENERGY, bonus);
    }
}
