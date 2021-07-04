package Controller.Menus;

import Model.Card;
import Model.Player;

public class GameMenu {
    private static String error;

    private static Player player1 = new Player(MainMenu.getUser());
    private static Player player2;
    private static Card selectedCard;

    public static void setPlayer2(Player player2) {
        GameMenu.player2 = player2;
    }

    public static boolean unselect() {
        return false;
    }

    public static boolean select(String cardAddress) {
        return false;
    }

    public static String getError() {
        return error;
    }

    public static boolean summon() {
        return false;
    }

    public static String nextPhase() {
        return null;
    }

    public static boolean set() {
        return false;
    }

    public static boolean setPosition(String positionToSet) {
        return false;
    }

    public static boolean flipSummon() {
        return false;
    }

    public static String attack() {
        return null;
    }

    public static String directAttack() {
        return null;
    }

    public static String attackToAMonster(String address) { // to check if address is valid or not
        return null;
    }

    public static boolean activeEffect() {
        return false;
    }

    public static void surrender() {

    }

    public static void initializeViewPlayers() {

    }
}
