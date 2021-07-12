package Controller.Menus;

import Controller.MenuController;
import Model.Player;
import Model.User;

public class DuelMenu {
    private static String error;

    public static boolean duel(String secondPlayer, String rounds) {
        if (User.getUserByUsername(secondPlayer) == null) {
            error = "there is no player with this username";
            return false;
        } else if (MenuController.getLoggedInUser().getActiveDeck() == null) {
            error = MenuController.getLoggedInUser().getUsername() + " has no active deck";
            return false;
        } else if (User.getUserByUsername(secondPlayer).getActiveDeck() == null) {
            error = secondPlayer + " has no active deck";
            return false;
        }
        else if (!MenuController.getLoggedInUser().getActiveDeck().isValid()) {
            error = MenuController.getLoggedInUser().getUsername()+"'s deck is invalid";
            return false;
        } else if (!User.getUserByUsername(secondPlayer).getActiveDeck().isValid()) {
            error = secondPlayer+"'s deck is invalid";
            return false;
        }
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
        if (MenuController.getLoggedInUser().getActiveDeck() == null) {
            error = MenuController.getLoggedInUser().getUsername() + " has no active deck";
            return false;
        } else if (!MenuController.getLoggedInUser().getActiveDeck().isValid()) {
            error = "your deck is invalid";
            return false;
        }
        else if (!rounds.equals("1") && !rounds.equals("3")) {
            error = "number of rounds is not supported";
            return false;
        }
        return true;
    }
}
