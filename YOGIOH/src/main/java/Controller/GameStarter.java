package Controller;

public class GameStarter extends  Thread {
    @Override
    public void run() {
        String result = Connection.sendMessageToTheServer("Can I start my game?");
    }
}
