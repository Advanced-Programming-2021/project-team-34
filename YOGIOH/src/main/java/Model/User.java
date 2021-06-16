package Model;

import java.util.ArrayList;

public class User {
    private static final ArrayList<User> users;

    private String username;
    private String password;
    private String nickname;
    private int coin;
    private int highScore;
    private ArrayList<Card> cards;
//    private ArrayList<Deck> decks;
//    private Deck activeDeck;

    static {
        users = new ArrayList<>();
    }

    {
        cards = new ArrayList<>();
//        decks = new ArrayList<>();
    }


    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

//    public Deck getActiveDeck() {
//        return activeDeck;
//    }


//    public ArrayList<Deck> getDecks() {
//        return decks;
//    }

    public void increaseCoin(int coinToIncrease) {
        this.coin += coinToIncrease;
    }

    public boolean doesHaveEnoughCoin(int coinsToBeChecked) {
        return this.coin >= coinsToBeChecked;
    }

    private boolean checkPassword(String passwordToBeChecked) {
        return password.equals(passwordToBeChecked);
    }

    public boolean checkPassword(String username, String password) {
        if (getUserByUsername(username) == null) return false;
        else return checkPassword(password);
    }

//    public boolean changePassword(String currentPassword, String newPassword) {
//        checkPassword(this.getUsername(), currentPassword);
//    }
}
