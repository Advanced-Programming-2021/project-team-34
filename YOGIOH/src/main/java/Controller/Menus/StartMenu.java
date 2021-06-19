package Controller.Menus;

import Controller.MenuController;
import Controller.MenuNames;
import Model.User;

public class StartMenu {
    private static String error = "There is no Error";

    public static String getError() {
        return error;
    }

    public static boolean login(String username, String password) {
        boolean success = User.checkPassword(username, password);
        if (success) {
            error = "There is no Error";
            MenuController.setMenuName(MenuNames.MainMenu);
            return true;
        } else {
            error = "Username and password didn't match";
            return false;
        }
    }

    public static boolean register(String username, String nickname, String password) {
        User user = User.getUserByUsername(username);
        if (user != null) {
            error = "user with username @"+username+" already exists";
            return false;
        }
        if (User.getUserByNickname(nickname) != null) {
            error = "user with nickname "+nickname +" already exists";
            return false;
        }
        user = new User(username, password, nickname);
        return true;
    }
}
