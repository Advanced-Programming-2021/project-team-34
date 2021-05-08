public class UserAndMenuController {

    public static Menus currentMenu = Menus.LOGIN_MENU;
    private static Player currentUser;

    public static void runTheProgram() {
        Controller.runLoginMenu();
    }

    private static void exitCurrentMenu() {
        switch (currentMenu) {
            case LOGIN_MENU -> currentMenu = Menus.EXIT;
            case MAIN_MENU -> currentMenu = Menus.LOGIN_MENU;
            default -> currentMenu = Menus.MAIN_MENU;
        }
    }

    public static void setUserLoggedIn(Player loggedInUser) {
        currentUser = loggedInUser;
    }

    public static void setUserLoggedOut() {
        currentUser = null;
    }

}
