package Controller.Menus;

import Model.Player;
import Model.User;

public class DuelMenu {
    private static String error;

    public static boolean duel(String secondPlayer, String rounds) {
        if (User.getUserByUsername(secondPlayer) == null) {
            error = "there is no player with this username";
            return false;
        } else if (MainMenu.getUser().getActiveDeck() == null) {
            error = MainMenu.getUser().getUsername() + " has no active deck";
            return false;
        } else if (User.getUserByUsername(secondPlayer).getActiveDeck() == null) {
            error = secondPlayer + " has no active deck";
            return false;
        } //*To Do: invalid active deck
        else if (!rounds.equals("1") && !rounds.equals("3")) {
            error = "number of rounds is not supported";
            return false;
        }
        GameMenu.setPlayer2(new Player(User.getUserByUsername(secondPlayer)));
        return true;
    }

    public static String getError() {
        return error;
    }

    public static boolean duelWithAI(String rounds) {
        if (MainMenu.getUser().getActiveDeck() == null) {
            error = MainMenu.getUser().getUsername() + " has no active deck";
            return false;
        } //*To Do: invalid active deck
        else if (!rounds.equals("1") && !rounds.equals("3")) {
            error = "number of rounds is not supported";
            return false;
        }
        return true;
    }
}
