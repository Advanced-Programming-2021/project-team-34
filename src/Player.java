import java.util.ArrayList;

public class Player {
    private String username;
    private String password;
    private String nickname;
    private int score;
    private int coin;
    //private ArrayList<Card> cards;
    private static ArrayList<Player> allPlayers = new ArrayList<>();
    //public ArrayList<Deck> decks;
    //private Deck activeDeck;


    public Player(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        allPlayers.add(this);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

//    public ArrayList<Card> getCards() {
//        return this.cards;
//    }

//    public void setCards(ArrayList<Card> cards) {
//        this.cards = cards;
//    }

//    public Deck getActiveDeck() {
//        return this.activeDeck;
//    }

//    public void activateDeck(Deck deckToActive) {
//        if (decks.contains((Deck) deckToActive)) {
//            activeDeck = deckToActive;
//        }
//    }

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

    public static ArrayList<Player> getAllPlayers() {
        return allPlayers;
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

}