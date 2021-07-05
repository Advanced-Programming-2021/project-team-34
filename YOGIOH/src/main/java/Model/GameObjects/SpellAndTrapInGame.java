package Model.GameObjects;

public class SpellAndTrapInGame extends CardInGame {
    String type = "spell or trap";

    @Override
    public String getType() {
        return type;
    }
}
