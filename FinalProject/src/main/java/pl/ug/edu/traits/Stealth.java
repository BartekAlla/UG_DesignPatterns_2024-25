package pl.ug.edu.traits;

public class Stealth extends BaseTrait{
    public Stealth(double value) {
        super(value);
    }

    @Override
    public TraitType getTraitType() {
        return TraitType.STEALTH;
    }

    @Override
    protected Trait createNew(double newValue) {
        return new Stealth(newValue);
    }
}
