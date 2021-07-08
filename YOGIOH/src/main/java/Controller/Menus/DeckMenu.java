package Controller.Menus;

import Controller.MenuController;
import Model.Card;
import Model.Deck;
import Model.User;


public class DeckMenu {
    private static String error;

    public static boolean createDeck(String deckName) {
        if (MenuController.getLoggedInUser().getDeckByName(deckName) != null) {
            error = "deck with name " + deckName + " already exist";
            return false;
        } else {
            Deck deck = new Deck();
            deck.setName(deckName);
            MenuController.getLoggedInUser().getDecks().add(deck);
            return true;
        }
    }

    public static String getError() {
        return error;
    }

    public static boolean deleteDeck(String deckName) {
        Deck deck = MenuController.getLoggedInUser().getDeckByName(deckName);
        if (deck == null) {
            error = "deck with name " + deckName + " does not exist";
            return false;
        } else {
            MenuController.getLoggedInUser().getDecks().remove(deck);
            return true;
        }
    }

    public static boolean activateDeck(String deckName) {
        Deck deck = MenuController.getLoggedInUser().getDeckByName(deckName);
        if (deck == null) {
            error = "deck with name " + deckName + " does not exist";
            return false;
        } else {
            MenuController.getLoggedInUser().setActiveDeck(deck);
            return true;
        }
    }

    public static boolean addCardToMainDeck(String deckName, String cardName) {
        User user = MenuController.getLoggedInUser();

        if (!user.getCards().contains(Card.getAllCards().get(cardName))) {
            error = "card with name " + cardName + " does not exist";
            return false;
        }
        if (user.getDeckByName(deckName) == null) {
            error = "deck with name " + deckName + " does not exist";
            return false;
        }
        if (user.getActiveDeck().getCardsInMainDeck().size() == 60) {
            error = "main deck is full";
            return false;
        }
        if (Deck.getCardFrequency(cardName, user) == 3) {
            error = "there are already three cards with name " + cardName + " in deck " + deckName;
            return false;
        }
        MenuController.getLoggedInUser().getActiveDeck().addCardToMainDeck(Card.getAllCards().get(cardName));
        return true;
    }

    public static boolean addCardToSideDeck(String deckName, String cardName) {
        User user = MenuController.getLoggedInUser();

        if (!user.getCards().contains(Card.getAllCards().get(cardName))) {
            error = "card with name " + cardName + " does not exist";
            return false;
        }
        if (user.getDeckByName(deckName) == null) {
            error = "deck with name " + deckName + " does not exist";
            return false;
        }
        if (user.getActiveDeck().getCardsInSideDeck().size() == 15) {
            error = "side deck is full";
            return false;
        }
        if (Deck.getCardFrequency(cardName, user) == 3) {
            error = "there are already three cards with name " + cardName + " in deck " + deckName;
            return false;
        }
        MenuController.getLoggedInUser().getActiveDeck().addCardToSideDeck(Card.getAllCards().get(cardName));
        return true;

    }

    public static boolean removeCardFromSideDeck(String deckName, String cardName) {
        User user = MenuController.getLoggedInUser();
        Deck deck = user.getDeckByName(deckName);
        if (deck == null){
            error = "deck with name " + deckName + " does not exist";
            return false;
        }
        if (!deck.isThereAnyCardInSideDeckWithName(cardName)){
            error = "card with name " + cardName + " does not exist in side deck";
            return false;
        }
        deck.getCardsInSideDeck().remove(Card.getAllCards().remove(cardName));
        return true;
    }

    public static boolean removeCardFromMainDeck(String deckName, String cardName) {
        User user = MenuController.getLoggedInUser();
        Deck deck = user.getDeckByName(deckName);
        if (deck == null){
            error = "deck with name " + deckName + " does not exist";
            return false;
        }
        if (!deck.isThereAnyCardInMainDeckWithName(cardName)){
            error = "card with name " + cardName + " does not exist in main deck";
            return false;
        }
        deck.getCardsInMainDeck().remove(Card.getAllCards().remove(cardName));
        return true;
    }

}
