import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ScoreBoard {

    private Player currentUser;
    private static ScoreBoard scoreBoard = null;
    private Scanner scanner;

    private ScoreBoard(Player currentUser) {
        this.currentUser = currentUser;
        runScoreBoardMenu();
    }

    public void runScoreBoardMenu() {
        while (UserAndMenuController.currentMenu == Menus.SCOREBOARD_MENU) {
            String userCommand = scanner.nextLine().trim();
            if (userCommand.equals("menu show-current")) System.out.println("Scoreboard Menu");
            else if (userCommand.startsWith("menu enter")) System.out.println("menu navigation is not possible");
            else if (userCommand.equals("scoreboard show"))showScoreboard();
        }
    }

    public static ScoreBoard getInstance(Player currUser) {
        if (scoreBoard == null) scoreBoard = new ScoreBoard(currUser);
        return scoreBoard;
    }


    public void showScoreboard() {
        //getting players from list
        ArrayList<Player> players = Player.getAllPlayers();
        //compare them based on score and alphabet
        Comparator<Player> allPlayersOrdered = Comparator.comparing(Player::getScore).thenComparing(Player::getNickname);
        
    }

    public void enterMenu() {
        ;
    }

    public void exitMenu() {
        ;
    }

    public void showCurrentMenu() {

    }
}
