import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;

public class DuellingMenu {

    private static DuellingMenu duellingMenu = null;
    private Player currentUser;
    private Player AiUser = Player.getPlayerByUsername("admin");

    public static DuellingMenu getInstance(Player currUser) {
        if (duellingMenu == null) duellingMenu = new DuellingMenu(currUser);
        duellingMenu.currentUser = currUser;
        return duellingMenu;
    }

    private DuellingMenu(Player currUser) {
        currentUser = currUser;
    }

    public void runDuelMenu() {
        while (UserAndMenuController.currentMenu == Menus.DUEL_MENU) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            Matcher matcher;

            if (Controller.getMatch(command, "menu show-current").matches())
                System.out.println(UserAndMenuController.currentMenu);
            else if (Controller.getMatch(command, "menu enter (.*)").matches())
                System.out.println("navigation is not possible");
            else if (Controller.getMatch(command, "menu exit").matches())
                UserAndMenuController.currentMenu = Menus.MAIN_MENU;
            else if ((matcher = Controller.getMatch(command, "^duel --new --ai --rounds (.+)$")).matches())
                newDuelAi(matcher);
            else if ((matcher = Controller.getMatch(command, "^duel --new --second-player (.+) --rounds (.+)$")).matches())
                newDuel(matcher);
            else System.out.println("invalid command");
        }
    }

    private void newDuelAi(Matcher matcher) {
        String round = matcher.group(1);
        if (currentUser.getActiveDeck() == null)
            System.out.println(currentUser.getUsername() + " has no active deck");
        else if (!currentUser.isDeckValid(currentUser.getActiveDeck().getName()))
            System.out.println(currentUser.getUsername() + "'s deck is not valid");
        else if (!(round.equals("1") || round.equals("3"))) System.out.println("number of rounds is not supported");
        else new Game(currentUser, new AI(AiUser));
    }

    private void newDuel(Matcher matcher) {

        String duelistName = matcher.group(1), round = matcher.group(2);

        if (Player.getPlayerByUsername(duelistName) == null)
            System.out.println("there is no player with this username");
        else if (currentUser.getActiveDeck() == null)
            System.out.println(currentUser.getUsername() + " has no active deck");
        else if (Objects.requireNonNull(Player.getPlayerByUsername(duelistName)).getActiveDeck() == null)
            System.out.println(duelistName + " has no active deck");
        else if (!Player.isDeckValid(currentUser.getActiveDeck().getName()))
            System.out.println(currentUser.getUsername() + "'s deck is not valid");
        else if (!Player.isDeckValid(Objects.requireNonNull(Player.getPlayerByUsername(duelistName)).getActiveDeck().getName()))
            System.out.println(duelistName + "'s deck is not valid");
        else if (!(round.equals("1") || round.equals("3"))) System.out.println("number of rounds is not supported");
        else if (round.equals("1")) oneRoundDuel(duelistName);
        else threeRoundDuel(duelistName);

    }

    private void oneRoundDuel(String duelistName) {
        int duelist1Wins = 0, duelist2Wins = 0;
        //round1
        Player duelist1 = currentUser;
        Player duelist2 = Player.getPlayerByUsername(duelistName);
        Game battlefield = new Game(duelist1, duelist2);

        //round1Finish
        if (battlefield.getWinner().getUsername().equals(currentUser.getUsername())) duelist1Wins++;
        else duelist2Wins++;
        System.out.println(battlefield.getWinner().getUsername() + " won the game and the score is: " + duelist1Wins + " - " + duelist2Wins);

        //matchFinish
        if (duelist1Wins == 1) {
            System.out.println(duelist1 + " won the whole match with score: " + duelist1Wins + " - " + duelist2Wins);
            duelist1.setScore(duelist1.getScore() + 1000);
            duelist1.setCoin(duelist1.getCoin() + 1000 + Board.duelist1.LP);
            duelist2.setCoin(duelist2.getCoin() + 100);
        } else {
            System.out.println(duelist2.getUsername() + " won the whole match with score: " + duelist1Wins + " - " + duelist2Wins);
            duelist2.setScore(duelist2.getScore() + 1000);
            duelist2.setCoin(duelist2.getCoin() + 1000 + Board.duelist2.LP);
            duelist1.setCoin(duelist1.getCoin() + 100);
        }
    }

    private void threeRoundDuel(String duelistName) {
        int duelist1Wins = 0, duelist2Wins = 0;

        //round1
        Player duelist1 = currentUser;
        Player duelist2 = Player.getPlayerByUsername(duelistName);
        Game battlefield = new Game(duelist1, duelist2);

        //round1Finish
        if (battlefield.getWinner().getName().equals(currentUser.getUsername())) duelist1Wins++;
        else duelist2Wins++;
        System.out.println(battlefield.getWinner().getName() + " won the game and the score is: " + duelist1Wins + " - " + duelist2Wins);
        int round1Duelist1Lp = Board.duelist1.LP, round1Duelist2Lp = Board.duelist2.LP;

        //add card from side


        //round2
        duelist1 = currentUser;
        duelist2 = Player.getPlayerByUsername(duelistName);
        battlefield = new Game(duelist1, duelist2);

        //round2Finish
        if (battlefield.getWinner().getName().equals(currentUser.getUsername())) duelist1Wins++;
        else duelist2Wins++;
        System.out.println(battlefield.getWinner().getName() + " won the game and the score is: " + duelist1Wins + " - " + duelist2Wins);
        int round2Duelist1Lp = Board.duelist1.LP, round2Duelist2Lp = Board.duelist2.LP;
        if (round2Duelist1Lp < round1Duelist1Lp) round2Duelist1Lp = round1Duelist1Lp;
        if (round2Duelist2Lp < round1Duelist2Lp) round2Duelist2Lp = round1Duelist2Lp;

        //checkMatchIsFinished
        if (duelist1Wins == 2) {
            finish2Round(duelist1Wins, duelist2Wins, duelist2, duelist1, round2Duelist1Lp);
            return;
        } else if (duelist2Wins == 2) {
            finish2Round(duelist1Wins, duelist2Wins, duelist1, duelist2, round2Duelist2Lp);
            return;
        }

        //add card from side

        //round3
        duelist1 =  currentUser;
        duelist2 =  Objects.requireNonNull(Player.getPlayerByUsername(duelistName));
        battlefield = new Game(duelist1, duelist2);

        //round3Finish
        if (battlefield.getWinner().getName().equals(currentUser.getUsername())) duelist1Wins++;
        else duelist2Wins++;
        System.out.println(battlefield.getWinner().getName() + " won the game and the score is: " + duelist1Wins + " - " + duelist2Wins);
        int round3Duelist1Lp = Board.duelist1.LP, round3Duelist2Lp = Board.duelist2.LP;
        if (round3Duelist1Lp < round2Duelist1Lp) round3Duelist1Lp = round2Duelist1Lp;
        if (round3Duelist2Lp < round2Duelist2Lp) round3Duelist2Lp = round2Duelist2Lp;

        //matchFinish
        if (duelist1Wins == 2) {
            System.out.println(duelist1.getUsername() + " won the whole match with score: " + duelist1Wins + " - " + duelist2Wins);
            duelist1.setScore(duelist1.getScore() + 3000);
            duelist1.setCoin(duelist1.getCoin() + 3000 + 3 * round3Duelist1Lp);
            duelist2.setCoin(duelist2.getCoin() + 300);
        } else {
            System.out.println(duelist2.getUsername() + " won the whole match with score: " + duelist1Wins + " - " + duelist2Wins);
            duelist2.setScore(duelist2.getScore() + 3000);
            duelist2.setCoin(duelist2.getCoin() + 3000 + 3 * round3Duelist2Lp);
            duelist1.setCoin(duelist1.getCoin() + 300);
        }
    }

    private void finish2Round(int duelist1Wins, int duelist2Wins, Player duelist1, Player duelist2, int round2Duelist2Lp) {
        System.out.println(duelist2.getUsername() + " won the whole match with score: " + duelist1Wins + " - " + duelist2Wins);
        duelist2.setScore(duelist2.getScore() + 3000);
        duelist2.setCoin(duelist2.getCoin() + 3000 + 3 * round2Duelist2Lp);
        duelist1.setCoin(duelist1.getCoin() + 300);
    }
}
