package pl.ug.edu.model.robot;

import pl.ug.edu.traits.Trait;
import pl.ug.edu.traits.TraitType;

import java.util.EnumMap;
import java.util.Map;

public class Robot {
    private final String name;
    private final Map<TraitType, Trait> traits;
    private int cloneCounter = 0;

    public Robot(String name) {
        this.name = name;
        this.traits = new EnumMap<>(TraitType.class);
    }

    public void addTrait(Trait trait) {
        traits.put(trait.getTraitType(), trait);
    }

    public Trait getTrait(TraitType traitType) {
        return traits.get(traitType);
    }

    public Map<TraitType, Trait> getTraits() {
        return traits;
    }

    public String getName() {
        return name;
    }

    public Robot copy() {
        cloneCounter++;
        Robot clone = new Robot(this.name + "_" + cloneCounter);
        for (Trait t : traits.values()) {
            clone.addTrait(t.copy());
        }
        return clone;
    }

    @Override
    public String toString() {
        return "Robot " + name + " with traits: " + traits.values();
    }

}
