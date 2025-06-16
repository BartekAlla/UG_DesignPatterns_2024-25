package pl.ug.edu.traits;

public class Armor extends BaseTrait {
    public Armor(double value) {
        super(value);
    }

    @Override
    public TraitType getTraitType() {
        return TraitType.ARMOR;
    }

    @Override
    protected Trait createNew(double newValue) {
        return new Armor(newValue);
    }
}
