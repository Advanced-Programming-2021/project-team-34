package View;

import Model.Deck;
import javafx.scene.control.Label;

public class DeckToSelect extends Label {
    Deck deck;
    boolean isActive = false;
    public DeckToSelect(Deck deck , boolean isActive) {
        this.deck = deck;
        this.setText((isActive) ? ("دک فعّال" + deck.getName()) : (deck.getName()));
    }
    public DeckToSelect(Deck deck) {
        this.deck = deck;
        this.setText((isActive) ? ("دک فعّال" + deck.getName()) : (deck.getName()));
    }

    public Deck getDeck() {
        return deck;
    }

    public boolean isActive() {
        return isActive;
    }
}
