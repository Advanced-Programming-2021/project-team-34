import Controller.CommandHelper.Command;
import Controller.CommandTypeInitializer;
import Model.Token;

public class Main {
    public static void main(String[] args) {
        CommandTypeInitializer.run();
        System.out.println(Command.help());
    }
}
