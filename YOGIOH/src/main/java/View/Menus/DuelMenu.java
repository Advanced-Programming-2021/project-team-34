package View.Menus;

import View.CommandHelper.Command;
import View.CommandHelper.CommandType;

public class DuelMenu extends ViewMenu {
    static boolean toContinue = true;
    public static void run() {
        initializeMenu();
        doMain();
    }

    private static void doMain() {
        String input;
        Command myCommand;
        String typeOfMyCommand;
        
    }

    private static void initializeMenu() {
        toContinue = true;
        Command.clearValidCommandTypes();
        initializeNewDuelCommandType();
        initializeNewDuelWithAICommandType();
    }

    private static void initializeNewDuelWithAICommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("duel");
        commandType.setName("duel with ai");
        commandType.addField("new");
        commandType.addField("ai");
        commandType.getField("ai").setDoesHaveArgument(false);
        commandType.getField("new").setDoesHaveArgument(false);
        commandType.addField("round");
        Command.addCommandType(commandType);
    }

    private static void initializeNewDuelCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("duel");
        commandType.setMainPart("duel");
        commandType.addField("new");
        commandType.getField("new").setDoesHaveArgument(false);
        commandType.addField("second-player");
        commandType.addField("round");
        Command.addCommandType(commandType);
    }
}
