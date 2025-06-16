package pl.ug.edu.traits;

public abstract class BaseTrait implements Trait {
    protected double value;

    public BaseTrait(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public abstract TraitType getTraitType();

    protected abstract Trait createNew(double newValue);

    @Override
    public Trait add(Trait other) {
        if (!this.getTraitType().equals(other.getTraitType())) {
            throw new IllegalArgumentException("Cannot add different trait types: " + this.getTraitType() + " vs " + other.getTraitType());
        }
        return createNew(this.value + other.getValue());
    }

    @Override
    public Trait copy() {
        return createNew(this.value);
    }

    @Override
    public String toString() {
        return getTraitType() + ": " + value;
    }
}
