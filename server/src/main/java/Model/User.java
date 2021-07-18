package Model;

import Exceptions.DuplicateNicknameException;
import Exceptions.DuplicateUsernameException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class User {
    public static ArrayList<User> users;
    static {
        users = new ArrayList<>();
    }

    private String username;
    private String password;
    private String nickname;
    private int coin;
    private int highScore;
    private ArrayList<Deck> decks = new ArrayList<Deck>();
    private Deck activeDeck;
    private HashMap<Card, Integer> cards = new HashMap<>();
    private static final int maximumNumberOfAvatar = 5;
    private String avatarName; /** address of avatar file */
    private int avatarInt;
    private GameRequest gameRequest = GameRequest.NO;
    {
//        decks = new ArrayList<>();
    }

    public User(String username, String password, String nickname) throws DuplicateUsernameException, DuplicateNicknameException {
        if (getUserByUsername(username) != null) {
            throw new DuplicateUsernameException(username);
        } else if (getUserByNickname(nickname) != null) {
            throw new DuplicateNicknameException(nickname);
        } else {
            this.username = username;
            this.password = password;
            this.nickname = nickname;
            this.coin = 40000;
            this.highScore = -1;
            this.avatarInt = (new Random().nextInt(maximumNumberOfAvatar - 1)) + 1;
            this.avatarName = "/Images/Avatars/" + (avatarInt) + ".png";
            setHighScore(0);
        }
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
                    users.remove(users.get(j + 1));
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

    public Deck getActiveDeck() {
        return activeDeck;
    }

    public void setActiveDeck(Deck activeDeck) { this.activeDeck = activeDeck; }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

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

    public HashMap<Card, Integer> getCards() {
        return cards;
    }

    public static User getUserByNickname(String nickname) {
        for (User user :
                users) {
            if (user.getNickname().equals(nickname)) {
                return user;
            }
        } return null;
    }

    public static void loadAllUsers() throws IOException {
        Gson gson = new Gson();
        users = gson.fromJson(new String(Files.readAllBytes(Paths.get("src/main/resources/allUsers.json"))),
                new TypeToken<List<User>>() {
                }.getType());
        for (User user : users) {
            String username = user.getUsername();
            File file = new File("src\\main\\resources\\data\\" + username + "\\cards.txt" );
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                if (Card.allCards.containsKey(name)) user.getCards().put(Card.allCards.get(name), 1);
            }
            file = new File("src\\main\\resources\\data\\" + username + "\\decks.txt");
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                Deck deck = new Deck();
                deck.setName(name);
                File file1 = new File("src\\main\\resources\\data\\" + username + "\\" + name + "Main.txt");
                Scanner scanner1 = new Scanner(file1);
                while (scanner1.hasNextLine()) {
                    String name1 = scanner1.nextLine();
                    if (Card.allCards.containsKey(name1)) deck.addCardToMainDeck(Card.getAllCards().get(name1));
                }
                File file2 = new File("src\\main\\resources\\data\\" + username + "\\" + name + "Side.txt");
                Scanner scanner2 = new Scanner(file2);
                while (scanner2.hasNextLine()) {
                    String name2 = scanner2.nextLine();
                    if (Card.allCards.containsKey(name2)) deck.addCardToSideDeck(Card.getAllCards().get(name2));
                }
                user.decks.add(deck);
            }
        }
    }

    public static void saveAllUsers() throws IOException {
        Gson gson = new Gson();
        FileWriter allUsersWriter = new FileWriter("src/main/resources/allUsers.json");
        allUsersWriter.write(gson.toJson(users));
        allUsersWriter.close();
    }
    public Deck getDeckByName(String deckName) {
        for (Deck deck :
                decks) {
            if (deck.getName().equals(deckName)) {
                return deck;
            }
        }
        return null;
    }

    /**
     * This method gets address of avatar file.
     * For example : "src/main/resources/Images/Avatars/1.png"
     * @return String address of file */
    public String getAvatarName() {
        return avatarName;
    }
    /**
     * This method changes avatar address to the next one !
     * */
    public void changeAvatar() {
        this.avatarInt = (avatarInt+1);
        if (avatarInt == maximumNumberOfAvatar+1) avatarInt = 1;
        this.avatarName =  "/Images/Avatars/"+(avatarInt)+".png";
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
    public Card getACardWithName(String name) {
        for (Map.Entry<Card, Integer> card : cards.entrySet()) {
            if (card.getKey().getName().equals(name)) {
                return card.getKey();
            }
        }return null;
    }

    public GameRequest getGameRequest() {
        return gameRequest;
    }

    public void setGameRequest(GameRequest gameRequest) {
        this.gameRequest = gameRequest;
    }
}
