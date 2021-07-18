package Controller.Menus;

import Controller.CommandHelper.Command;
import Controller.Connection;
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
        String result = Connection.sendMessageToTheServer("login username "+username+
                " password "+password);
        Command command = new Command(result);
        if (command.getType().equals("signup or login successfully")) {
            MenuController.setToken(command.getField("token"));
            return true;
        } else {
            error = result;
            return false;
        }
    }

    public static boolean register(String username , String nickname , String password) {
        String result = Connection.sendMessageToTheServer("signup username "+username+
                " nickname "+nickname+" password "+password);
        Command command = new Command(result);
        if (command.getType().equals("signup or login successfully")) {
            return true;
        } else {
            error = result;
            return false;
        }
    }

//    public static boolean register(String username, String nickname, String password) {
//        if (User.getUserByUsername(username) != null) {
//            error = "user with username @" + username + " already exists";
//            return false;
//        }
//        if (User.getUserByNickname(nickname) != null) {
//            error = "user with nickname " + nickname + " already exists";
//            return false;
//        }
//        new User(username, password, nickname);
//        try {
//            File file = new File("src\\main\\resources\\data\\" + username);
//            file.mkdir();
//            File file1 = new File("src\\main\\resources\\data\\" + username + "\\cards.txt");
//            file1.createNewFile();
//            File file2 = new File("src\\main\\resources\\data\\" + username + "\\decks.txt");
//            file2.createNewFile();
//        } catch (Exception e) {
//            System.out.println("could not save user");
//            e.printStackTrace();
//        }
//        return true;
//    }
}
