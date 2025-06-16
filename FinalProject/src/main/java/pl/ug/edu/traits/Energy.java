package pl.ug.edu.traits;

public class Energy extends BaseTrait {
    public Energy(double value) {
        super(value);
    }

    @Override
    public TraitType getTraitType() {
        return TraitType.ENERGY;
    }

    @Override
    protected Trait createNew(double newValue) {
        return new Energy(newValue);
    }
}
