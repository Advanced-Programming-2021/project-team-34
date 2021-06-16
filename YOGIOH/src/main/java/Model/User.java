package Model;

import java.util.ArrayList;

public class User {
    private static final ArrayList<User> users;

    static {
        users = new ArrayList<>();
    }

    private final String username;
    private String password;
    private String nickname;
    private int coin;
    private int highScore;
    //    private ArrayList<Deck> decks;
//    private Deck activeDeck;
    private final ArrayList<Card> cards;

    {
        cards = new ArrayList<>();
//        decks = new ArrayList<>();
    }

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.coin = 0;
        this.highScore = -1;
        setHighScore(0);
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public static boolean checkPassword(String username, String passwordToBeChecked) {
        if (getUserByUsername(username) == null) return false;
        else return getUserByUsername(username).checkPassword(passwordToBeChecked);
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
        // TO-DO
        // save data in file
        if (highScore > this.highScore) {
            this.highScore = highScore;
            if (this.highScore == 0) {
                users.add(this);
                return;
            }
            sortUsers();
        }
    }

    private void sortUsers() {
        users.remove(this);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getHighScore() < this.getHighScore()) {
                users.add(users.get(users.size() - 1));
                for (int j = users.size() - 2; j >= i; j--) {
                    if (j != i) users.add(j, users.get(j - 1));
                    else users.add(j, this);
                }
                return;
            }
        }
        users.add(this);
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

//    public Deck getActiveDeck() {
//        return activeDeck;
//    }


//    public ArrayList<Deck> getDecks() {
//        return decks;
//    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void increaseCoin(int coinToIncrease) {
        this.coin += coinToIncrease;
    }

    public boolean doesHaveEnoughCoin(int coinsToBeChecked) {
        return this.coin >= coinsToBeChecked;
    }

    private boolean checkPassword(String passwordToBeChecked) {
        return password.equals(passwordToBeChecked);
    }


    public boolean changePassword(String currentPassword, String newPassword) {
        if (checkPassword(currentPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    public int getRank() {
        int ans = 1;
        for (User user :
                users) {
            if (user.getHighScore() > this.highScore) {
                ans++;
            }
        }
        return ans;
    }
}
