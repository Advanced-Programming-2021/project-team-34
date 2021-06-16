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


    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;

    }

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
        if (highScore>this.highScore) {
            this.highScore = highScore;
            // TO-DO
            // order in arraylist users
            // save data in file
        }
    }

    public String getUsername() {
        return username;
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

    public boolean changePassword(String currentPassword, String newPassword) {
        if (checkPassword(currentPassword)) {
            this.password = newPassword;
            return true;
        } return false;
    }
}
