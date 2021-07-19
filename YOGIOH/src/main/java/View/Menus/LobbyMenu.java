package View.Menus;

public class LobbyMenu extends ViewMenu {
    static LobbyMenu lobbyMenu = new LobbyMenu();
    public static void run() {
        try {
            lobbyMenu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
