package Controller;

import Model.User;

import java.io.IOException;

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
            }
            try {
                User.saveAllUsers();
            } catch (IOException e) {
                System.out.println("There is a problem in saving users");
                e.printStackTrace();
            }
        }
    }
}
