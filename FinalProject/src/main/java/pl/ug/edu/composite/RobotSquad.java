package pl.ug.edu.composite;

import pl.ug.edu.rules.EnhancementRule;
import pl.ug.edu.traits.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class RobotSquad implements RobotUnit {
    private final String name;
    private final List<RobotUnit> members = new ArrayList<>();
    private final List<EnhancementRule> rules = new ArrayList<>();
    private final Map<TraitType, Trait> bonusTraits = new EnumMap<>(TraitType.class);

    public RobotSquad(String name) {
        this.name = name;
    }

    public void addMember(RobotUnit unit) {
        members.add(unit);
    }

    public void addRule(EnhancementRule rule) {
        rules.add(rule);
    }

    public void evaluateRules() {
        for (EnhancementRule rule : rules) {
            if (rule.condition(this)) {
                rule.apply(this);
            }
        }
    }

    public void modifyTrait(TraitType type, double delta) {
        Trait existing = bonusTraits.get(type);
        Trait bonus = createTraitOfType(type, delta);

        if (existing == null) {
            bonusTraits.put(type, bonus);
        } else {
            bonusTraits.put(type, existing.add(bonus));
        }
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

        for (Map.Entry<TraitType, Trait> entry : bonusTraits.entrySet()) {
            total.merge(entry.getKey(), entry.getValue().getValue(), Double::sum);
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

    private Trait createTraitOfType(TraitType type, double value) {
        return switch (type) {
            case POWER -> new Power(value);
            case ENERGY -> new Energy(value);
            case ARMOR -> new Armor(value);
            case INTELLIGENCE -> new Intelligence(value);
        };
    }
}
