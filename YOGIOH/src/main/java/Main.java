import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Model.User.loadAllUsers();
        } catch (IOException e) {
            System.out.println("There is a problem in loading users");
            e.printStackTrace();
        }
        Controller.MenuController.run();
    }
}
