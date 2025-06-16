package pl.ug.edu.rules;

import pl.ug.edu.composite.RobotSquad;
import pl.ug.edu.traits.TraitType;

public class TeamSizeBoostsPowerRule implements EnhancementRule {
    @Override
    public boolean condition(RobotSquad squad) {
        return squad.getMemberCount() >= 5;
    }

    @Override
    public void apply(RobotSquad squad) {
        squad.modifyTrait(TraitType.POWER, 100);
    }
}
