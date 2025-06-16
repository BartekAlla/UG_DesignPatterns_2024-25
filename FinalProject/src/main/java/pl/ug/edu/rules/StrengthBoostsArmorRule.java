package pl.ug.edu.rules;

import pl.ug.edu.composite.RobotSquad;
import pl.ug.edu.traits.TraitType;

public class StrengthBoostsArmorRule implements EnhancementRule {
    @Override
    public boolean condition(RobotSquad squad) {
        return squad.getTraitValue(TraitType.POWER) > 800;
    }

    @Override
    public void apply(RobotSquad squad) {
        squad.modifyTrait(TraitType.ARMOR, 50);
    }
}
