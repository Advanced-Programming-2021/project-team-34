package Controller.Menus;

public class StartMenu {
    private static String loginError = "There is no Error";

    public static String getError() {
        return loginError;
    }

    public static boolean login(String username, String password) {
        loginError = "There is no Error";
        return true;
    }

    public static boolean register(String username, String nickname, String password) {
        //TO-DO
        return true;
    }
}
