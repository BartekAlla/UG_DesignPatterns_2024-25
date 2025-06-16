package pl.ug.edu.composite;

import pl.ug.edu.traits.TraitType;

import java.util.Map;

public interface RobotUnit {
    double getTraitValue(TraitType traitType);
    Map<TraitType, Double> getAllTraitValues();
    String getName();
}
