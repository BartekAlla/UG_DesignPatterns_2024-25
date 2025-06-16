package pl.ug.edu.mission;

import pl.ug.edu.composite.RobotUnit;
import pl.ug.edu.traits.TraitType;

import java.util.EnumMap;
import java.util.Map;

public class Mission {
    private final String name;
    private final Map<TraitType, Double> requiredTraits;

    public Mission(String name, Map<TraitType, Double> requiredTraits) {
        this.name = name;
        this.requiredTraits = new EnumMap<>(requiredTraits);
    }

    public String getName() {
        return name;
    }

    public Map<TraitType, Double> getRequiredTraits() {
        return requiredTraits;
    }

    public boolean isSatisfiedBy(RobotUnit unit) {
        for (Map.Entry<TraitType, Double> entry : requiredTraits.entrySet()) {
            double actualValue = unit.getTraitValue(entry.getKey());
            if (actualValue < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Mission: " + name + "\nRequired Traits:\n");
        for (Map.Entry<TraitType, Double> entry : requiredTraits.entrySet()) {
            sb.append("- ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
