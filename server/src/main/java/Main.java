import Controller.CommandHelper.Command;
import Controller.CommandTypeInitializer;
import Controller.Connection;
import Model.Shop;
import Model.Token;

public class Main {
    public static void main1(String[] args) {
        CommandTypeInitializer.run();
        System.out.println(Command.help());
    }

    public static void main(String[] args) {
        Shop.initializeHashMaps();
        CommandTypeInitializer.run();
        Connection.run();
    }
}
