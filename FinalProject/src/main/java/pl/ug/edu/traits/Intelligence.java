package pl.ug.edu.traits;

public class Intelligence extends BaseTrait {
    public Intelligence(double value) {
        super(value);
    }

    @Override
    public TraitType getTraitType() {
        return TraitType.INTELLIGENCE;
    }

    @Override
    protected Trait createNew(double newValue) {
        return new Intelligence(newValue);
    }
}
