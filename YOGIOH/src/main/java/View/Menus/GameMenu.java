package View.Menus;

import View.CommandHelper.Command;
import View.CommandHelper.CommandType;

public class GameMenu extends ViewMenu {
    static boolean toContinue = true;
    public static void run() {
        initializeMenu();
    }

    private static void initializeMenu() {
        toContinue = true;
        Command.clearValidCommandTypes();
        initializeSelectCardCommandType();
    }

    private static void initializeSelectCardCommandType() {
        CommandType commandType = new CommandType();
        //select <card address>
        commandType.setMainPart("");
        commandType.setName("select card");
        commandType.addField("select");
        Command.addCommandType(commandType);
    }
}
