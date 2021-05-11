import java.util.ArrayList;

public class Player {
    private String username;
    private String password;
    private String nickname;
    private int score;
    private int coin;
    private ArrayList<Card> cards;
    private static ArrayList<Player> allPlayers = new ArrayList<Player>();
    public ArrayList<Deck> decks;
    private Deck activeDeck;


    public Player(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
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

    public boolean changePassword(String newPassword , String oldPassword) {
        if(this.password.equals(oldPassword)){
            this.password = newPassword;
            return true;
        }else{
            return false;
        }
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
    public void createDeck(String name) {
        Deck deck = new Deck(name, this);
        decks.add(deck);
    }
    public boolean deleteDeck(Deck deck) {
        if(decks.contains(deck)){
            decks.remove(deck);
            return true;
        }else{return false;}
    }
    /**
     * 
     * @param card
     * @param deck
     * @param inToMainDeck : true -> add to the main deck ; false -> add to the side deck
     */
    public void addCardToDeck(Card card, Deck deck , boolean inToMainDeck) {
        if(inToMainDeck){
            deck.addCardToMainDeck(card);
        }else{
            deck.addCardToSideDeck(card);
        }
    }
    /**
     * 
     * @param card
     * @return false if card was in cards , true if card wasn't in cards and added
     */
    public boolean addCard(Card card) {
        if(this.cards.contains(card)){
            return false;
        }
        this.cards.add(card);
        return true;
    }
    /**
     * 
     * @param card
     * @param deck
     * @return false if card wasn't in deck , true if card ommited successfully
     */
    public boolean removeCardFromDeck(Card card , Deck deck){
        return deck.deleteCardFromDeck(card);
    }
    public boolean showDeck(Deck deck) {
        if(!decks.contains(deck)){
            return false;
        }deck.show();
        return true;
    }
    public void showDecks() {
        for (Deck deck : deck) {
            deck.show();
        }
    }
    public void showAllCards() {
        for (Card card : cards) {
            card.show();
        }
    }

}