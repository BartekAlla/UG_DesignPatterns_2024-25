package pl.ug.edu.rules;

import pl.ug.edu.composite.RobotSquad;

public interface EnhancementRule {
    boolean condition(RobotSquad squad);
    void apply(RobotSquad squad);
}
