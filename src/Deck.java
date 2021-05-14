import java.util.ArrayList;

public class Deck {
    private String name;
    private ArrayList<Card> mainDeck;
    private ArrayList<Card> sideDeck;
    private Player owner;

    public Deck(String name, Player owner) {
        this.name = name;
        this.owner = owner;
    }
    //start of setters and getters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Card> getMainDeck() {
        return this.mainDeck;
    }

    /*public void setMainDeck(ArrayList<Card> mainDeck) {
        this.mainDeck = mainDeck;
    }*/

    public ArrayList<Card> getSideDeck() {
        return this.sideDeck;
    }
    /*
    public void setSideDeck(ArrayList<Card> sideDeck) {
        this.sideDeck = sideDeck;
    }*/

    public Player getOwner() {
        return this.owner;
    }
    /*
    public void setOwner(Player owner) {
        this.owner = owner;
    }*/
    //end of setters and getters
    protected void addCardToMainDeck(Card card) {
        mainDeck.add(card);
    }

    protected void addCardToSideDeck(Card card) {
        sideDeck.add(card);
    }

    protected boolean deleteCardFromDeck(Card card) {/* Returnts true if remove successfully and false if card is not in the deck */
        if(mainDeck.contains(card)){
            mainDeck.remove(card);
            return true;
        }else if(sideDeck.contains(card)){
            sideDeck.remove(card);
            return true;
        }
        return false;
    }

    private void transferCard(Card card) {
        if (mainDeck.contains(card)) {
            mainDeck.remove(card);
            sideDeck.add(card);
        } else if (sideDeck.contains(card)) {
            sideDeck.remove(card);
            mainDeck.add(card);
        }
    }
    //TO DO
    public void show() {
        System.out.println("To program!!!");
    }
}
