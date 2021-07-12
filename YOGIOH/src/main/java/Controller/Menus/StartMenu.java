package Controller.Menus;

import Controller.MenuController;
import Controller.MenuNames;
import Model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
            MenuController.setLoggedInUser(User.getUserByUsername(username));
            return true;
        } else {
            error = "Username and password didn't match";
            return false;
        }
    }

    public static boolean register(String username, String nickname, String password) throws IOException {
        if (User.getUserByUsername(username) != null) {
            error = "user with username @" + username + " already exists";
            return false;
        }
        if (User.getUserByNickname(nickname) != null) {
            error = "user with nickname " + nickname + " already exists";
            return false;
        }
        new User(username, password, nickname);
        File file = new File("C:\\Users\\MSI\\Desktop\\mnop\\project-team-34-master\\YOGIOH\\src\\main\\resources\\data\\" + username);
        file.mkdir();
        File file1 = new File("C:\\Users\\MSI\\Desktop\\mnop\\project-team-34-master\\YOGIOH\\src\\main\\resources\\data\\" + username + "\\cards.txt");
        file1.createNewFile();
        File file2 = new File("C:\\Users\\MSI\\Desktop\\mnop\\project-team-34-master\\YOGIOH\\src\\main\\resources\\data\\" + username + "\\decks.txt");
        file2.createNewFile();
        return true;
    }
}
