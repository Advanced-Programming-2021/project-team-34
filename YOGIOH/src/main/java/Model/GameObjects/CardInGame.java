package Model.GameObjects;

import Model.Card;

public class CardInGame {
    CardInGameState state = CardInGameState.IN_DECK;
    Card card;

    public Card getCard() {
        return card;
    }

    public CardInGameState getState() {
        return state;
    }
}
