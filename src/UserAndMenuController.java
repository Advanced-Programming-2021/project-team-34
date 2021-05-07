public class UserAndMenuController {

    public static Menus currentMenu = Menus.LOGIN_MENU;
    public static Player currentUser;

    public static void runTheProgram() {
        Controller.runLoginMenu();
    }

    private static void exitCurrentMenu() {
        switch (currentMenu) {
            case LOGIN_MENU:
                currentMenu = Menus.EXIT;
                break;
            case MAIN_MENU:
                currentMenu = Menus.LOGIN_MENU;
                break;
            default:
                currentMenu = Menus.MAIN_MENU;
                break;
        }
    }

    public static void setUserLoggedIn(Player loggedInUser) {
        currentUser = loggedInUser;
    }

    public static void setUserLoggedOut() {
        currentUser = null;
    }

}
