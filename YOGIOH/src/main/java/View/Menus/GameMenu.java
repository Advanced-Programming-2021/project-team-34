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
        initializeSelectCardCommandType(); // to forget the selected card , the "select" field of it should be "d"
        initializeSummonCommandType();
        initializeSetCommandType();
        initializeSetPositionCommandType();
        initializeFlipSummonCommandType();
        initializeAttackCommandType();
        initializeActiveEffectCommandType();
        initializeShowGraveyardCommandType();
        initializeBackCommandType();
        initializeShowCommandType();
        initializeCancelCommandType();
        initializeSurrenderCommandType();
    }

    private static void initializeSurrenderCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("surrender");
        commandType.setName("surrender");
        Command.addCommandType(commandType);
    }

    private static void initializeCancelCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("cancel");
        commandType.setMainPart("cancel");
        Command.addCommandType(commandType);
    }

    private static void initializeShowCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("card show selected");
        Command.addCommandType(commandType);
    }

    private static void initializeBackCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("back");
        commandType.setName("back");
        Command.addCommandType(commandType);
    }

    private static void initializeShowGraveyardCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("show graveyard");
        commandType.setMainPart("show graveyard");
        Command.addCommandType(commandType);
    }

    private static void initializeActiveEffectCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("active effect");
        commandType.setName("active effect");
        Command.addCommandType(commandType);
    }

    private static void initializeAttackCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("");
        commandType.setName("attack");
        commandType.addField("attack");
        Command.addCommandType(commandType);
    }

    private static void initializeFlipSummonCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("flip summon");
        commandType.setMainPart("flip-summon");
        Command.addCommandType(commandType);
    }

    private static void initializeSetPositionCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("set position");
        commandType.setMainPart("set");
        commandType.addField("position");
        Command.addCommandType(commandType);
    }

    private static void initializeSetCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("set");
        commandType.setMainPart("set");
        Command.addCommandType(commandType);
    }

    private static void initializeSummonCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("summon");
        commandType.setName("summon");
        Command.addCommandType(commandType);
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
