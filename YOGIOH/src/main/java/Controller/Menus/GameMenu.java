package Controller.Menus;

import Controller.MenuController;
import Model.GameObjects.CardInGame;
import Model.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMenu {
    private static String error;

    private static final Player player1 = new Player(MenuController.getLoggedInUser());
    private static Player player2;
    private static Player currentPlayer = player1;
    private static Player otherPlayer = player2;
    private static CardInGame selectedCard;
    private static GamePhase phase = GamePhase.DrawPhase;

    public static void setPlayer2(Player player2) {
        GameMenu.player2 = player2;
    }

    public static boolean unselect() {
        if (selectedCard == null) {
            error = "no card is selected yet";
            return false;
        }
        return true;
    }

    public static boolean select(String cardAddress) {
        Pattern pattern = Pattern.compile("^(monster|spell|monster opponent|spell opponent|hand) ([123456])$");
        Matcher matcher = pattern.matcher(cardAddress);
        if (matcher.find()) {
            int number = Integer.parseInt(matcher.group(2));
            switch (matcher.group(1)) {
                case "monster":
                    return selectingMonster(number);
                case "spell":
                    return selectingSpell(number);
                case "monster opponent":
                    return selectingOpponentMonster(number);
                case "spell opponent":
                    return selectingOpponentSpell(number);
                case "hand":
                    return selectingCardOfHand(number);
            }
        } else {
            switch (cardAddress) {
                case "field":
                    return selectingFieldZone(currentPlayer);
                case "field opponent":
                    return selectingFieldZone(otherPlayer);
                default:
                    error = "invalid selection";
                    return false;
            }
        }
        return false;
    }

    private static boolean selectingMonster(int number) {
        return monsterNumberError(number, currentPlayer);
    }

    private static boolean selectingOpponentMonster(int number) {
        return monsterNumberError(number, otherPlayer);
    }

    private static boolean monsterNumberError(int number, Player currentPlayer) {
        if (number == 6) {
            error = "invalid selection";
            return false;
        } else if (currentPlayer.getMonstersOnTheField().get(number - 1) == null) {
            error = "no card found in the given position";
            return false;
        } else {
            selectedCard = currentPlayer.getMonstersOnTheField().get(number - 1);
            return true;
        }
    }

    private static boolean selectingSpell(int number) {
        return spellNumberError(number, currentPlayer);
    }

    private static boolean selectingOpponentSpell(int number) {
        return spellNumberError(number, otherPlayer);
    }

    private static boolean spellNumberError(int number, Player currentPlayer) {
        if (number == 6) {
            error = "invalid selection";
            return false;
        } else if (currentPlayer.getSpellAndTrapsOnTheField().get(number - 1) == null) {
            error = "no card found in the given position";
            return false;
        } else {
            selectedCard = currentPlayer.getSpellAndTrapsOnTheField().get(number - 1);
            return true;
        }
    }

    private static boolean selectingCardOfHand(int number) {
        if (currentPlayer.getInHandCards().get(number - 1) == null) {
            error = "no card found in the given position";
            return false;
        } else {
            selectedCard = currentPlayer.getInHandCards().get(number - 1);
            return true;
        }
    }

    private static boolean selectingFieldZone(Player player) {
        if (player.getFZ() == null) {
            error = "no card found in the given position";
            return false;
        } else {
            selectedCard = player.getFZ();
            return true;
        }
    }

    public static String getError() {
        return error;
    }

    public static boolean summon() {
        if (selectedCard == null) {
            error = "no card is selected yet";
            return false;
        }
        return false;
    }

    public static String nextPhase() {
        switch (phase) {
            case DrawPhase:
                phase = GamePhase.StandbyPhase;
                return "phase: Standby phase";
            case StandbyPhase:
                phase = GamePhase.MainPhase1;
                return "phase: MainPhase1";
            case MainPhase1:
                phase = GamePhase.BattlePhase;
                return "phase: BattlePhase";
            case BattlePhase:
                phase = GamePhase.MainPhase2;
                return "phase: MainPhase2";
            case MainPhase2:
                phase = GamePhase.EndPhase;
                return "phase: EndPhase";
            case EndPhase:
                phase = GamePhase.DrawPhase;
                View.Menus.GameMenu.changeTurn();
                GameMenu.changeTurn();
                return "phase: DrawPhase";
        }
        return null;
    }

    private static void changeTurn() {
        Player player = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = player;
    }

    public static boolean set() {
        if (selectedCard == null) {
            error = "no card is selected yet";
            return false;
        } else {
            boolean isInHand = false;
            for (CardInGame card : currentPlayer.getInHandCards()) {
                if (card == selectedCard) {
                    isInHand = true;
                    break;
                }
            }
            if (!isInHand) {
                error = "you can’t set this card";
                return false;
            } else {

            }
        }
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
