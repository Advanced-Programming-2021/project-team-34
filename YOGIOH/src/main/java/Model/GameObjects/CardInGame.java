package Model.GameObjects;

import Model.Card;

public class CardInGame {
    CardInGameState state = CardInGameState.IN_DECK;
    Card card;
    String type;

    public Card getCard() {
        return card;
    }

    public CardInGameState getState() {
        return state;
    }

    public String getType() {
        return type;
    }

    public void setState(CardInGameState state) {
        this.state = state;
    }
}
