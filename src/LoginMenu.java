public class LoginMenu {

    private static Player loginned = null;

    public static void createUser(String username, String password, String nickname) {
        if (Player.getPlayerByUsername(username) != null)
            System.out.println("user with username " + username + " already exists");
        else if (Player.getPlayerByNickname(nickname) != null)
            System.out.println("user with nickname " + nickname + " already exists");
        else {
            new Player(username, password, nickname);
            System.out.println("user created successfully!");
        }
    }

    public static void login(String username, String password) {
        if (Player.getPlayerByUsername(username) == null) System.out.println("Username and password didn't match!");
        else if (!Player.getPlayerByUsername(username).getPassword().equals(password))
            System.out.println("Username and password didn't match!");
        else {
            loginned = Player.getPlayerByUsername(username);
            System.out.println("user logged in successfully!");
        }
    }

    public static void menuEnter(String menu) {
        if (menu.equals("MainMenu")) {
            if (loginned == null) System.out.println("please login first");
            else {
                Controller.runMainMenu(loginned);
            }
        } else System.out.println("menu navigation is not possible");
    }

    public static void menuExit() {
        ;
    }

    public static void showCurrentMenu() {
        ;
    }

}
