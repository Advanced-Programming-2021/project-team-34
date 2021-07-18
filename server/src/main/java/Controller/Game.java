package Controller;

import Model.Player;

public class Game { // todo : complete the body and check if needed extends Thread
    Player player1 , player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
}
