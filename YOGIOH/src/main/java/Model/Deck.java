package Model;

import java.util.ArrayList;
import java.util.Comparator;

public class Deck {
    ArrayList<Card> cardsInMainDeck = new ArrayList<Card>();
    ArrayList<Card> cardsInSideDeck = new ArrayList<Card>();
    Card cardInFZ;
    String name;
    String error = "No Error!";
    boolean locked = false;// should be true if we are playing in 3-round mode
    int numberOfCardsInMainDeck;// to check that size of arrayList don't change when it is locked
    int numberOfCardsInSideDeck;// to check that size of arrayList don't change when it is locked

    public int getNumberOfCardsInMainDeck() {
        return cardsInMainDeck.size();
    }
    public int getNumberOfCardsInSideDeck() {
        return cardsInSideDeck.size();
    }
    public boolean isAnyCardInFZ() {
        return cardInFZ != null;
    }
    public ArrayList<Card> getCardsInMainDeck() {
        return cardsInMainDeck;
    }
    public ArrayList<Card> getCardsInSideDeck() {
        return cardsInSideDeck;
    }
    public Card getCardInFZ() {
        return cardInFZ;
    }
    public void setCardInFZ(Card cardInFZ) {
        this.cardInFZ = cardInFZ;
    }
    public void addCardToMainDeck(Card card) {
        if (card == null) {
            return;
        }
        if (cardsInMainDeck.contains(card)) {return;}
        cardsInMainDeck.add(card);
        if (!locked) {
            numberOfCardsInMainDeck++;
        }
    }
    public void addCardToSideDeck(Card card) {
        if (card == null) {
            return;
        }
        if (cardsInSideDeck.contains(card)) {return;}
        if (!locked) {
            numberOfCardsInSideDeck++;
        }
        cardsInSideDeck.add(card);
    }
    public boolean deleteCardFromMainDeck(Card card) {
        if (card == null) {
            return false;
        }
        boolean result = cardsInMainDeck.remove(card);
        if (result && !locked) {
            numberOfCardsInMainDeck--;
        }
        return result;
    }
    public boolean deleteCardFromSideDeck(Card card) {
        if (card == null) {
            return false;
        }
        boolean result = cardsInSideDeck.remove(card);
        if (result && !locked) {
            numberOfCardsInSideDeck--;
        }
        return result;
    }
    public boolean deleteCardFromMainDeck(String name) {
        for (Card card :
                cardsInMainDeck) {
            if (card.getName().equals(name)) {
                cardsInMainDeck.remove(card);
                return true;
            }
        }
        return false;
    }
    public boolean deleteCardFromSideDeck(String name) {
        for (Card card :
                cardsInSideDeck) {
            if (card.getName().equals(name)) {
                cardsInSideDeck.remove(card);
                return true;
            }
        }
        return false;
    }
    public boolean isThereAnyCardInMainDeckWithName(String name) {
        return getACardInMainDeckWithName(name)!=null;
    }
    public boolean isThereAnyCardInSideDeckWithName(String name) {
        return getACardInSideDeckWithName(name)!=null;
    }
    public Card getACardInMainDeckWithName(String name) {
        for (Card card :
                cardsInMainDeck) {
            if (card.getName().equals(name)) {
                return card;
            }
        } return null;
    }
    public Card getACardInSideDeckWithName(String name) {
        for (Card card :
                cardsInSideDeck) {
            if (card.getName().equals(name)) {
                return card;
            }
        } return null;
    }
    public boolean sendCardFromSideDeckToMainDeck(Card card) {
        if (cardsInSideDeck.contains(card)) {
            cardsInSideDeck.remove(card);
            cardsInMainDeck.add(card);
            if (!locked) {
                numberOfCardsInMainDeck++;
                numberOfCardsInSideDeck--;
            }
            return true;
        } else {
            return false;
        }
    }
    public boolean sendCardFromMainDeckToSideDeck(Card card) {
        if (cardsInMainDeck.contains(card)) {
            cardsInMainDeck.remove(card);
            cardsInSideDeck.add(card);
            if (!locked) {
                numberOfCardsInSideDeck++;
                numberOfCardsInMainDeck--;
            }
            return true;
        } else {
            return false;
        }
    }
    public boolean sendCardFromSideDeckToMainDeck(String name) {
        Card card = getACardInSideDeckWithName(name);
        if (card == null) {
            return false;
        } return sendCardFromSideDeckToMainDeck(card);
    }
    public boolean sendCardFromMainDeckToSideDeck(String name) {
        Card card = getACardInMainDeckWithName(name);
        if (card == null) {
            return false;
        } return sendCardFromMainDeckToSideDeck(card);
    }
    private int numberOfCardsInDeckWithName(String name) {
        int number = 0;
        for (Card card :
                cardsInMainDeck) {
            if (card.getName().equals(name)) {
                number++;
            }
        }
        for (Card card :
                cardsInSideDeck) {
            if (card.getName().equals(name)) {
                number++;
            }
        }
        return number;
    }
    public boolean isValid() {
        error = "No Error!";
        if (!(cardsInSideDeck.size() == numberOfCardsInSideDeck && cardsInMainDeck.size() == numberOfCardsInMainDeck)) {
            error = "cards in side deck should be "+numberOfCardsInSideDeck+" but they are "+cardsInSideDeck.size()+
                    " and cards in main deck should be "+numberOfCardsInMainDeck+" but they are "+cardsInMainDeck.size();
            return false;
        }
        if (cardsInMainDeck.size() < 40) {
            error = "cards in main deck should be at least 40 but now they are only "+cardsInMainDeck.size();
            return false;
        }
        if (cardsInSideDeck.size() > 60) {
            error = "cards in main deck should be at most 60 but now they are "+ cardsInMainDeck.size();
            return false;
        }
        for (Card card :
                cardsInMainDeck) {
            if (numberOfCardsInDeckWithName(card.getName()) > 3) {
                error = "every card in deck should be at most 3 but card "+
                        card.getName()+" is "+numberOfCardsInDeckWithName(card.getName());
                return false;
            }
        }
        for (Card card :
                cardsInSideDeck) {
            if (numberOfCardsInDeckWithName(card.getName()) > 3) {
                error = "every card in deck should be at most 3 but card "+
                        card.getName()+" is "+numberOfCardsInDeckWithName(card.getName());
                return false;
            }
        }
        if (cardsInSideDeck.size() > 15) {
            error = "cards in side deck should be at most 15 but now they are "+ cardsInSideDeck.size();
            return false;
        }


        return true;
    }
    public void setLocked() {
        locked = true;
    }
    public void setUnlocked() {
        locked = false;
    }

    public String getError() {
        return error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sortCards() {
        cardsInSideDeck.sort(new Comparator<Card>() {
            @Override
            public int compare(Card card, Card t1) {
                return String.CASE_INSENSITIVE_ORDER.compare(card.getName(), t1.getName());
            }
        });
        cardsInMainDeck.sort(new Comparator<Card>() {
            @Override
            public int compare(Card card, Card t1) {
                return String.CASE_INSENSITIVE_ORDER.compare(card.getName(), t1.getName());
            }
        });
    }

}