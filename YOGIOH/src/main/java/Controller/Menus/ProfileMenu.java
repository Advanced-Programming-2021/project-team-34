package Controller.Menus;

import Controller.MenuController;
import Model.User;

public class ProfileMenu {
    static String error = "there is no error yet";
    public static boolean changeNickname(String nickname) {
        if (User.getUserByNickname(nickname) == null) {
            error = "user with nickname "+nickname+"already exists!";
            return false;
        }
        MenuController.getLoggedInUser().setNickname(nickname);
        return true;
    }

    public static String getError() {
        return error;
    }

    public static boolean changePassword(String currentPassword, String newPassword) {
        boolean success = MenuController.getLoggedInUser().changePassword(currentPassword, newPassword);
        if (success) {
            return true;
        }
        error = "current password is invalid!";
        return false;
    }
}
