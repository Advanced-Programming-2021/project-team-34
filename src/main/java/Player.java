import java.util.ArrayList;

public class Player {

    private String username;
    private String password;
    private String nickname;
    private int score;
    private int coins;
    private ArrayList<Card> cards;
    private static ArrayList<Player> allPlayers;
    public ArrayList<Deck> decks;
    private Deck activeDeck;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public static Player getPlayerByUsername(String username) {
        for (Player player : allPlayers) {
            if (player.getUsername().equals(username)) {
                return player;
            }
        }
        return null;
    }

    public static Player getPlayerByNickname(String nickname) {
        for (Player player : allPlayers) {
            if (player.getNickname().equals(nickname)) {
                return player;
            }
        }
        return null;
    }

    public Player(String username, String password, String nickname) {
        setUsername(username);
        setNickname(nickname);
        setPassword(password);
        allPlayers.add(this);
    }

    public void increaseScore(int score) {
        this.score += score;
    }

    public void increaseCoins(int coins) {
        this.coins += coins;
    }

    public void decreaseCoins(int coins) {
        this.coins -= coins;
    }

    public void addCard(Card card) {

    }

    public void addCardToDeck(Card card, boolean flag) {

    }

    public void createDeck() {

    }

    public void deleteDeck(Deck deck) {

    }

    public void removeCardFromDeck(Deck deck, boolean flag) {

    }

    public void setActiveDeck(Deck deck) {

    }

    public void showDecks() {

    }

    public void showDeck(Deck deck, boolean flag) {

    }

    public void showAllCards() {

    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }
}
