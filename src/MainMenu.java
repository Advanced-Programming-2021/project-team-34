import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {

    private boolean isCurrentUserLoggedOut = false;
    private Player currentUser;
    private static Scanner scanner;

    public MainMenu(Player newUser) {
        currentUser = newUser;
        String userCommand = scanner.nextLine().trim();

        while (!userCommand.equals("menu exit") && !isCurrentUserLoggedOut) {
            if (userCommand.startsWith("menu enter")) runChangingMenu(userCommand, currentUser);
            else if (userCommand.equals("user logout")) runLoggingOutTheCurrentUser();
            else if (userCommand.equals("menu show-current")) System.out.println("Main Menu");
            else System.out.println("invalid command");

            if (!isCurrentUserLoggedOut) userCommand = scanner.nextLine();
        }
    }

    private void runLoggingOutTheCurrentUser() {
        UserAndMenuController.setUserLoggedOut();
        currentUser.setUserLoggedInOrOut(false);
        System.out.println("logout successfully");
        isCurrentUserLoggedOut = true;
    }

    public static void runChangingMenu(String userCommand, Player currentUser) {
        Matcher matcher = Controller.getMatch(userCommand,"");
        if (matcher.find()) {
            switch (matcher.group(1)) {
                case "Profile":
                    UserAndMenuController.currentMenu = Menus.PROFILE_MENU;
                    ProfileMenu.runProfileMenu(currentUser);
                    break;
                case "Shop":
                    UserAndMenuController.currentMenu = Menus.SHOP_MENU;
                    ShoppingMenu.getInstance(currentUser).runShoppingMenu(currentUser);
                    break;
                case "Deck":
                    UserAndMenuController.currentMenu = Menus.DECK_MENU;
                    DecksMenu.getInstance(currentUser).runDeckMenu();
                    break;
                case "Duel":
                    UserAndMenuController.currentMenu = Menus.DUEL_MENU;
                    DuellingMenu.getInstance(currentUser).runDuelMenu();
                    break;
                case "ScoreBoard":
                    UserAndMenuController.currentMenu = Menus.SCOREBOARD_MENU;
                    ScoreBoard.getInstance(currentUser).runScoreBoardMenu();
            }
        }
        else System.out.println("invalid command");
    }


}
