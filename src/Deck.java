//package src;
import java.util.ArrayList;
public class Deck {
    private String name;
    private ArrayList<Card> mainDeck;
    private ArrayList<Card> sideDeck;
    private Player owner;
    
    public void addCardToMainDeck(Card card) {
        mainDeck.add(card);
    }
    public void addCardToSideDeck(Card card) {
        sideDeck.add(card);
    }
    public void transferCard(Card card) {
        if(mainDeck.contains(card)){
            mainDeck.remove(card);
            sideDeck.add(card);
        }else if(sideDeck.contains(card)){
            sideDeck.remove(card);
            mainDeck.add(card);
        }
    }
}
