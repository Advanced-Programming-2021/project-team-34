import javax.swing.*;
import java.util.ArrayList;

public class Player {
    private String username;
    private String password;
    private String nickname;
    private int score;
    private int coin;
    private ArrayList<String> cards;
    private static ArrayList<Player> allPlayers = new ArrayList<>();
    public static ArrayList<Deck> decks;
    private Deck activeDeck;
    private boolean isUserLoggedIn;


    public Player(String username, String password, String nickname) {
        setUsername(username);
        setPassword(password);
        setNickname(nickname);
        isUserLoggedIn = true;
        allPlayers.add(this);
    }

    //start of setters and getters
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCoin() {
        return this.coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public ArrayList<String> getCards() {
        return this.cards;
    }

    public void setCards(ArrayList<String> cards) {
        this.cards = cards;
    }

    public Deck getActiveDeck() {
        return this.activeDeck;
    }

    public void setActiveDeck(Deck activeDeck) {
        this.activeDeck = activeDeck;
    }

    public void setUserLoggedInOrOut(boolean isUserLoggedIn) {
        this.isUserLoggedIn = isUserLoggedIn;
    }

    public boolean isUserLoggedIn() {
        return isUserLoggedIn;
    }

    public static ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    //end of setters and getters
    public void increaseCoins(int coin) {
        this.coin += coin;
    }

    public void decreaseCoins(int coin) {
        this.coin -= coin;
    }

    public void increaseScore(int score) {
        this.score += score;
    }

    public void decreaseScore(int score) {
        this.score -= score;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public static Player getPlayerByUsername(String username) {
        for (Player player : allPlayers) {
            if (player.getUsername().equals(username)) return player;
        }
        return null;
    }

    public static Player getPlayerByNickname(String nickname) {
        for (Player player : allPlayers) {
            if (player.getNickname().equals(nickname)) return player;
        }
        return null;
    }

    public boolean createDeck(String name) {
        for (Deck deck : decks) {
            if (deck.getName().equals(name)) {
                System.out.println("deck with name " + name + " already exist");
                return false;
            }
        }
        Deck deck = new Deck(name, this);
        decks.add(deck);
        System.out.println("deck created successfully");
        return true;
    }

    public boolean deleteDeck(String deckName) {
        for (Deck deck : decks) {
            if (deck.getName().equals(deckName)) {
                decks.remove(deck);
                System.out.println("deck deleted successfully");
                return true;
            }
        }
        System.out.println("deck with name " + deckName + " does not exist");
        return false;
    }

    /**
     * @param card
     * @param deck
     * @param inToMainDeck : true -> add to the main deck ; false -> add to the side deck
     */
    public void addCardToDeck(Card card, Deck deck, boolean inToMainDeck) {
        if (inToMainDeck) {
            deck.addCardToMainDeck(card);
        } else {
            deck.addCardToSideDeck(card);
        }
    }

    //!!!we can have 3 cards of each, so this evaluation is not accurate
//    /**
//     *
//     * @param card
//     * @return false if card was in cards , true if card wasn't in cards and added
//     */
//    public boolean addCard(Card card) {
//        if(this.cards.contains(card)){
//            return false;
//        }
//        this.cards.add(card);
//        return true;
//    }

    /**
     * @param card
     * @param deck
     * @return false if card wasn't in deck , true if card ommited successfully
     */
    public boolean removeCardFromDeck(Card card, Deck deck) {
        return deck.deleteCardFromDeck(card);
    }

    public boolean showDeck(Deck deck) {
        if (!decks.contains(deck)) {
            return false;
        }
        deck.show();
        return true;
    }

    public void showDecks() {
        for (Deck deck : decks) {
            deck.show();
        }
    }

    //show cards according to their name
    public void showAllCards() {
        for (String card : cards) {
            ShowCard.showSelectedCard(card);
        }
    }

    public Deck getDeckByName(String deckName) {
        for (Deck deck : decks) {
            if (deck.getName().equals(deckName)) return deck;
        }
        return null;
    }

}