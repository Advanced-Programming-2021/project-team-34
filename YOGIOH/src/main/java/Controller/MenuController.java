package Controller;

import Model.User;

public class MenuController {
    private static MenuNames menuName = MenuNames.StartMenu;
    private static boolean toContinue = true;
    private static User loggedInUser = null;
    public static void setMenuName(MenuNames menuName) {
        MenuController.menuName = menuName;
    }
    public static User getLoggedInUser() {
        return loggedInUser;
    }
    public static void setLoggedInUser(User loggedInUser) {
        MenuController.loggedInUser = loggedInUser;
    }

    public static void run() {
        while (toContinue) {
            switch (menuName) {
                case StartMenu:
                    View.Menus.StartMenu.run();
                    break;
                case Exit:
                    toContinue = false;
                    break;
                case ProfileMenu:
                    View.Menus.ProfileMenu.run();
                    break;
                case MainMenu:
                    View.Menus.MainMenu.run();
                    break;
                case DuelMenu:
                    View.Menus.GameMenu.run();
                    break;
                case DeckMenu:
                    View.Menus.DeckMenu.run();
                    break;
                case ScoreBoardMenu:
                    View.Menus.ScoreBoardMenu.run();
                    break;
                case ShopMenu:
                    View.Menus.ShopMenu.run();
                    break;
                default:
                    System.out.println("There is a problem. Tell Mahdi Teymoori Anar to correct it.");
                    break;
            }
        }
    }
}
