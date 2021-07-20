import Model.User;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        try {
//            User.loadAllUsers();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        new View.Menus.CheatMenu().start();
        Controller.MenuController.run();
    }
}
