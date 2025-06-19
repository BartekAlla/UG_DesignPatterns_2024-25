package pl.ug.edu.composite;

import pl.ug.edu.traits.TraitType;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class RobotSquad implements RobotUnit {
    private final String name;
    private final List<RobotUnit> members = new ArrayList<>();

    public RobotSquad(String name) {
        this.name = name;
    }

    public void addMember(RobotUnit unit) {
        members.add(unit);
    }

    @Override
    public Map<TraitType, Double> getAllTraitValues() {
        Map<TraitType, Double> total = new EnumMap<>(TraitType.class);

        for (RobotUnit unit : members) {
            Map<TraitType, Double> traits = unit.getAllTraitValues();
            for (Map.Entry<TraitType, Double> entry : traits.entrySet()) {
                total.merge(entry.getKey(), entry.getValue(), Double::sum);
            }
        }

        return total;
    }

    @Override
    public double getTraitValue(TraitType type) {
        return getAllTraitValues().getOrDefault(type, 0.0);
    }

    @Override
    public String getName() {
        return name;
    }

    public int getMemberCount() {
        return members.size();
    }

    public List<RobotUnit> getMembers() {
        return members;
    }
}
