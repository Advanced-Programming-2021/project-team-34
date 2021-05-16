import java.util.ArrayList;

public class Board {
    private char[][] player1Board;
    private char[][] player2Board;
    private ArrayList<Card> player1Graveyard;
    private ArrayList<Card> player2Graveyard;
    private ArrayList<Card> player1Hand;
    private ArrayList<Card> player2Hand;
    private ArrayList<Card> player1Monsters;
    private ArrayList<Card> player2Monsters;
    private ArrayList<Card> player1TrapsAndSpells;
    private ArrayList<Card> player2TrapsAndSpells;
    private ArrayList<Card> player1RemainedDeck, player2RemainedDeck;
    private Card fz1 , fz2;
    private Player you, opponent, player1, player2;

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
}
