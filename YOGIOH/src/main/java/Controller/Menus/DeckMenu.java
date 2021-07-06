package Controller.Menus;

import Controller.MenuController;
import Model.Deck;

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
        return false;
    }

    public static boolean addCardToSideDeck(String deckName, String cardName) {
        return false;
    }

    public static boolean removeCardFromSideDeck(String deckName, String cardName) {
        return false;
    }

    public static boolean removeCardFromMainDeck(String deckName, String cardName) {
        return false;
    }
}
