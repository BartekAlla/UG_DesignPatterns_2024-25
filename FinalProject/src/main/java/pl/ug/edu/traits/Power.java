package pl.ug.edu.traits;

public class Power extends BaseTrait {
    public Power(double value) {
        super(value);
    }

    @Override
    public TraitType getTraitType() {
        return TraitType.POWER;
    }

    @Override
    protected Trait createNew(double newValue) {
        return new Power(newValue);
    }
}
