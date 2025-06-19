package pl.ug.edu.traits;

public interface Trait {
    TraitType getTraitType();

    double getValue();

    Trait add(Trait other);

    Trait copy();
}
