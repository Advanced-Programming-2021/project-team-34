//package src;
import java.util.ArrayList;
public class Deck {
    private String name;
    private ArrayList<Card> mainDeck;
    private ArrayList<Card> sideDeck;
    private Player owner;

    public Deck(String name , Player owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getMainDeck() {
        return this.mainDeck;
    }

    public void setMainDeck(ArrayList<Card> mainDeck) {
        this.mainDeck = mainDeck;
    }

    public ArrayList<Card> getSideDeck() {
        return this.sideDeck;
    }

    public void setSideDeck(ArrayList<Card> sideDeck) {
        this.sideDeck = sideDeck;
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
    
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
