import java.util.ArrayList;

public class Board {
    private char[][] player1Board;
    private char[][] player2Board;
    public ArrayList<Card> player1Graveyard;
    public ArrayList<Card> player2Graveyard;
    public ArrayList<Card> player1Hand;
    public ArrayList<Card> player2Hand;
    public ArrayList<Card> player1Monsters;
    public ArrayList<Card> player2Monsters;
    public ArrayList<Card> player1TrapsAndSpells;
    public ArrayList<Card> player2TrapsAndSpells;
    public ArrayList<Card> player1RemainedDeck, player2RemainedDeck;
    private Card fz1 , fz2;
    private Player you, opponent, player1, player2;

    public void addToHand(Player player , Card card){
        if(player.equals(player1)||player==player1){
            player1Hand.add(card);
        }else {
            player2Hand.add(card);
        }
    }
    public Board(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void show(Player you){
        this.you = you;
        if (this.player1.equals(you)||player1==you){
            opponent = player2;
        }else {
            opponent = player1;
        }
        showOpponentBoard();
        System.out.println("\n\n--------------------------\n");
        showYourBoard();
    }
    private void showOpponentBoard(){
        showNickname(opponent);
        goToNextColumn();
        showLifePoint(opponent);
        goToNextRow();
        goToNextColumn();
        showInHandCards(opponent);
        goToNextRow();
        showDN(opponent);
        goToNextRow();
        showSpellsAndTraps(opponent);
        goToNextRow();
        showMonsters(opponent);
        showGY(opponent);
        goToNextColumn();goToNextColumn();goToNextColumn();goToNextColumn();goToNextColumn();goToNextColumn();
        showFZ(opponent);
    }
    private  void  showYourBoard(){
        showFZ(you);
        goToNextColumn();goToNextColumn();goToNextColumn();goToNextColumn();goToNextColumn();goToNextColumn();
        showGY(you);
        showMonsters(you);
        goToNextRow();
        showSpellsAndTraps(you);
        goToNextRow();
        showDN(you);
        goToNextRow();
        showInHandCards(you);
        goToNextRow();
        showLifePoint(you);
        goToNextColumn();
        showNickname(you);
    }
    private void showNickname(Player player){
        write(player.getNickname());
    }
    private void showLifePoint(Player  player){
        write(""+(player.lifePoint));
    }
    private void showInHandCards(Player player){
        if(player.equals(player1)||player==player1){
            for (Card c :
                    player1Hand) {
                System.out.println("c");
                goToNextColumn();
            }
        }else {
            for (Card c :
                    player2Hand) {
                System.out.println("c");
                goToNextColumn();
            }
        }
    }
    private void showDN(Player player){
        if (player.equals(player1)||player==player1 ){
            write(""+player1RemainedDeck.size());
        }else {
            write(""+player2RemainedDeck.size());
        }
    }
    private void showGY(Player player){
        if(player.equals(player1)||player==player1){
            write(""+player1Graveyard.size());
        }else{
            write(""+player2Graveyard.size());
        }
    }
    private void showSpellsAndTraps(Player player){
        if((player.equals(player1)||player==player1)&&(player.equals(you))||player==you){
            write(""+player1Board[0][4]);goToNextColumn();
            write(""+player1Board[0][2]);goToNextColumn();
            write(""+player1Board[0][1]);goToNextColumn();
            write(""+player1Board[0][3]);goToNextColumn();
            write(""+player1Board[0][5]);
        }else if((player.equals(player2)||player==player2)&&(player.equals(you))||player==you){
            write(""+player2Board[0][4]);goToNextColumn();
            write(""+player2Board[0][2]);goToNextColumn();
            write(""+player2Board[0][1]);goToNextColumn();
            write(""+player2Board[0][3]);goToNextColumn();
            write(""+player2Board[0][5]);
        }else if((player.equals(player2)||player==player2)&&(player.equals(opponent))||player==opponent){
            write(""+player2Board[0][5]);goToNextColumn();
            write(""+player2Board[0][3]);goToNextColumn();
            write(""+player2Board[0][1]);goToNextColumn();
            write(""+player2Board[0][2]);goToNextColumn();
            write(""+player2Board[0][4]);
        }else{
            write(""+player1Board[0][5]);goToNextColumn();
            write(""+player1Board[0][3]);goToNextColumn();
            write(""+player1Board[0][1]);goToNextColumn();
            write(""+player1Board[0][2]);goToNextColumn();
            write(""+player1Board[0][4]);
        }
    }
    private void showMonsters(Player player){
        if((player.equals(player1)||player==player1)&&(player.equals(you))||player==you){
            write(""+player1Board[1][4]);goToNextColumn();
            write(""+player1Board[1][2]);goToNextColumn();
            write(""+player1Board[1][1]);goToNextColumn();
            write(""+player1Board[1][3]);goToNextColumn();
            write(""+player1Board[1][5]);
        }else if((player.equals(player2)||player==player2)&&(player.equals(you))||player==you){
            write(""+player2Board[1][4]);goToNextColumn();
            write(""+player2Board[1][2]);goToNextColumn();
            write(""+player2Board[1][1]);goToNextColumn();
            write(""+player2Board[1][3]);goToNextColumn();
            write(""+player2Board[1][5]);
        }else if((player.equals(player2)||player==player2)&&(player.equals(opponent))||player==opponent){
            write(""+player2Board[1][5]);goToNextColumn();
            write(""+player2Board[1][3]);goToNextColumn();
            write(""+player2Board[1][1]);goToNextColumn();
            write(""+player2Board[1][2]);goToNextColumn();
            write(""+player2Board[1][4]);
        }else{
            write(""+player1Board[1][5]);goToNextColumn();
            write(""+player1Board[1][3]);goToNextColumn();
            write(""+player1Board[1][1]);goToNextColumn();
            write(""+player1Board[1][2]);goToNextColumn();
            write(""+player1Board[1][4]);
        }
    }
    private void showFZ(Player player){
        if (player.equals(player1)||player==player1){
            if (fz1 == null) {
                write("E");
            }else {write ("O");}
        }else{
            if (fz2 == null) {
                write(("E"));
            }else {write("O");}
        }
    }
//    public void showPlayer1Board() {
//
//    }
//
//    public void showPlayer2Board() {
//
//    }
//
//    public void getPlayer1Board() {
//
//    }
//
//    public void getPlayer2Board() {
//
//    }
//
//    public void getPlayer1Graveyard() {
//
//    }
//
//    public void getPlayer2Graveyard() {
//
//    }
//
//    public void getPlayer1Hand() {
//
//    }
//
//    public void getPlayer2Hand() {
//
//    }
//
//    public void getPlayer1Monsters() {
//
//    }
//
//    public void getPlayer2Monsters() {
//
//    }
//
//    public void getPlayer1TrapsAndSpells() {
//
//    }
//
//    public void getPlayer2TrapsAndSpells() {
//
//    }
    public static void goToNextRow(){
        System.out.println("");
    }
    public static void goToNextColumn(){
        System.out.println("\t");
    }
    public static void write(String string){
        System.out.println(string);
    }
//    public static void write(char character){
//        System.out.println(character);
//    }
    //start of setters and getters

    public char[][] getPlayer1Board() {
        return player1Board;
    }

    public void setPlayer1Board(char[][] player1Board) {
        this.player1Board = player1Board;
    }

    public char[][] getPlayer2Board() {
        return player2Board;
    }

    public void setPlayer2Board(char[][] player2Board) {
        this.player2Board = player2Board;
    }

    public ArrayList<Card> getPlayer1Graveyard() {
        return player1Graveyard;
    }

    public void setPlayer1Graveyard(ArrayList<Card> player1Graveyard) {
        this.player1Graveyard = player1Graveyard;
    }

    public ArrayList<Card> getPlayer2Graveyard() {
        return player2Graveyard;
    }

    public void setPlayer2Graveyard(ArrayList<Card> player2Graveyard) {
        this.player2Graveyard = player2Graveyard;
    }

    public ArrayList<Card> getPlayer1Hand() {
        return player1Hand;
    }

    public void setPlayer1Hand(ArrayList<Card> player1Hand) {
        this.player1Hand = player1Hand;
    }

    public ArrayList<Card> getPlayer2Hand() {
        return player2Hand;
    }

    public void setPlayer2Hand(ArrayList<Card> player2Hand) {
        this.player2Hand = player2Hand;
    }

    public ArrayList<Card> getPlayer1Monsters() {
        return player1Monsters;
    }

    public void setPlayer1Monsters(ArrayList<Card> player1Monsters) {
        this.player1Monsters = player1Monsters;
    }

    public ArrayList<Card> getPlayer2Monsters() {
        return player2Monsters;
    }

    public void setPlayer2Monsters(ArrayList<Card> player2Monsters) {
        this.player2Monsters = player2Monsters;
    }

    public ArrayList<Card> getPlayer1TrapsAndSpells() {
        return player1TrapsAndSpells;
    }

    public void setPlayer1TrapsAndSpells(ArrayList<Card> player1TrapsAndSpells) {
        this.player1TrapsAndSpells = player1TrapsAndSpells;
    }

    public ArrayList<Card> getPlayer2TrapsAndSpells() {
        return player2TrapsAndSpells;
    }

    public void setPlayer2TrapsAndSpells(ArrayList<Card> player2TrapsAndSpells) {
        this.player2TrapsAndSpells = player2TrapsAndSpells;
    }

    public ArrayList<Card> getPlayer1RemainedDeck() {
        return player1RemainedDeck;
    }

    public void setPlayer1RemainedDeck(ArrayList<Card> player1RemainedDeck) {
        this.player1RemainedDeck = player1RemainedDeck;
    }

    public ArrayList<Card> getPlayer2RemainedDeck() {
        return player2RemainedDeck;
    }

    public void setPlayer2RemainedDeck(ArrayList<Card> player2RemainedDeck) {
        this.player2RemainedDeck = player2RemainedDeck;
    }

    public Card getFz1() {
        return fz1;
    }

    public void setFz1(Card fz1) {
        this.fz1 = fz1;
    }

    public Card getFz2() {
        return fz2;
    }

    public void setFz2(Card fz2) {
        this.fz2 = fz2;
    }

    //end of setters and getters
}
