package Model.GameObjects;

public class CardInGame {
    CardInGameState state = CardInGameState.IN_OUR_DECK;

    public CardInGameState getState() {
        return state;
    }
}
