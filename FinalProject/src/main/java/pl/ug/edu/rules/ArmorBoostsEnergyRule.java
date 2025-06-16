package pl.ug.edu.rules;

import pl.ug.edu.composite.RobotSquad;
import pl.ug.edu.traits.TraitType;

public class ArmorBoostsEnergyRule implements EnhancementRule {
    @Override
    public boolean condition(RobotSquad squad) {
        return squad.getTraitValue(TraitType.ARMOR) > 1000;
    }

    @Override
    public void apply(RobotSquad squad) {
        squad.modifyTrait(TraitType.ENERGY, 200);
    }
}
