package Controller;

import View.Menus.StartMenu;

public class MenuController {
    private static MenuNames menuName = MenuNames.StartMenu;
    private static boolean toContinue = true;
    public static void setMenuName(MenuNames menuName) {
        MenuController.menuName = menuName;
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

            }
        }
    }
}
