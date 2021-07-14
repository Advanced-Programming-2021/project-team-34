package Controller.Menus;

import Controller.MenuController;
import Model.Card;
import Model.Deck;
import Model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class DeckMenu {
    private static String error;

    public static boolean createDeck(String deckName) {
        if (MenuController.getLoggedInUser().getDeckByName(deckName) != null) {
            error = "deck with name " + deckName + " already exist";
            return false;
        } else {
            Deck deck = new Deck();
            deck.setName(deckName);
            try {
                FileWriter writer = new FileWriter("src\\main\\resources\\data\\" + MenuController.getLoggedInUser().getUsername() + "decks.txt");
                writer.append(deckName).append("\n");
                writer.close();
                File file = new File("C:\\Users\\MSI\\Desktop\\mnop\\project-team-34-master\\YOGIOH\\src\\main\\resources\\data\\" + MenuController.getLoggedInUser().getUsername() + "\\" + deckName + "Main.txt");
                file.createNewFile();
                File file1 = new File("C:\\Users\\MSI\\Desktop\\mnop\\project-team-34-master\\YOGIOH\\src\\main\\resources\\data\\" + MenuController.getLoggedInUser().getUsername() + "\\" + deckName + "Side.txt");
                file1.createNewFile();
            } catch (Exception e) {
                System.out.println("could not save deck");
                e.printStackTrace();
            }
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

    /**
     * @author : mahditeymoorianar
     * This method is called by view and adds a card with name to a main deck of current user by name
     *      same as DOC page 14
     * */
    public static boolean addCardToMainDeck(String deckName, String cardName) {
        Deck deck = MenuController.getLoggedInUser().getDeckByName(deckName);
        Card card = MenuController.getLoggedInUser().getACardWithName(cardName);
        if (card == null) {
            error = "card with name "+cardName+" does not exist";
        }
        if (deck == null) {
            error = "deck with name "+deckName+" does not exist";
            return false;
        }
        if ((deck.getCardsInMainDeck().size()>=60)) {
            error = "main deck is full";
            return false;
        }
        if (deck.numberOfCardsInDeckWithName(cardName) >= 3) {
            error = "there are already three cards with name "+cardName+" in deck "+deckName;
            return false;
        }
        deck.addCardToMainDeck(card);
        try {
            FileWriter writer = new FileWriter("src\\main\\resources\\data\\" + MenuController.getLoggedInUser().getUsername() + "\\" + deckName + "Main.txt");
            writer.append(cardName).append("\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("could not save card name on main deck");
            e.printStackTrace();
        }
        return true;
    }
    /**
     * @author : mahditeymoorianar
     * This method is called by view and adds a card with name to a side deck of current user by name
     *      same as DOC page 14
     * */
    public static boolean addCardToSideDeck(String deckName, String cardName) {
        Deck deck = MenuController.getLoggedInUser().getDeckByName(deckName);
        Card card = MenuController.getLoggedInUser().getACardWithName(cardName);
        if (card == null) {
            error = "card with name "+cardName+" does not exist";
        }
        if (deck == null) {
            error = "deck with name "+deckName+" does not exist";
            return false;
        }
        if (deck.getCardsInSideDeck().size()>=15) {
            error = "side deck is full";
            return false;
        }
        if (deck.numberOfCardsInDeckWithName(cardName) >= 3) {
            error = "there are already three cards with name "+cardName+" in deck "+deckName;
            return false;
        }
        deck.addCardToSideDeck(card);
        try {
            FileWriter writer = new FileWriter("src\\main\\resources\\data\\" + MenuController.getLoggedInUser().getUsername() + "\\" + deckName + "Side.txt");
            writer.append(cardName).append("\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("could not save card name on side deck");
            e.printStackTrace();
        }
        return true;
    }

    /**
     * modified by mahditeymoorianar
     * */
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
        deck.deleteCardFromSideDeck(cardName);
        return true;
    }

    /**
     * modified by mahditeymoorianar
     * */
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
        deck.deleteCardFromMainDeck(cardName);
        return true;
    }

}
