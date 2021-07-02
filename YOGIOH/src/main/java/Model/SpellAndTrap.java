package Model;

public class SpellAndTrap extends Card {
    boolean itIsSpellAndNotTrap;
    String spellAndTrapType;

    public SpellAndTrap(String name) {
        super(name);
    }

    public String getSpellAndTrapType() {
        return spellAndTrapType;
    }

    public boolean isSpell() {
        return itIsSpellAndNotTrap;
    }
    public boolean isTrap() {
        return !itIsSpellAndNotTrap;
    }

    public boolean isItIsSpellAndNotTrap() {
        return itIsSpellAndNotTrap;
    }
}
