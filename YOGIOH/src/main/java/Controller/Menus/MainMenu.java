package Controller.Menus;

import Model.User;

public class MainMenu {
    private static User user;

    public static void setUser(User user) {
        MainMenu.user = user;
    }

    public static User getUser() {
        return user;
    }
}
