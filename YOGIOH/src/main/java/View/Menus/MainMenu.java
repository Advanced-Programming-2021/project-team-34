package View.Menus;

import Controller.MenuController;
import Controller.MenuNames;
import View.CommandHelper.Command;
import View.CommandHelper.CommandType;

public class MainMenu extends ViewMenu {
    private static boolean toContinue = true;
    public static void run() {
        initializeMenu();
        doMain();
    }

    private static void doMain() {
        String input;
        Command myCommand;
        String typeOfMyCommand;
        while (toContinue) {
            show("MAIN MENU");
            input = input();
            myCommand = new Command(input);
            typeOfMyCommand = myCommand.getType();
            switch (typeOfMyCommand) {
                case "invalid type":
                    print("The command you inputted is not valid in this menu!!!");
                    break;
                case "exit":
                    MenuController.setMenuName(MenuNames.StartMenu);
                    toContinue = false;
                    break;
                case "show current menu":
                    print("Login Menu");
                    break;
                case "enterProfileMenu":
                    MenuController.setMenuName(MenuNames.ProfileMenu);
                    toContinue = false;
                    break;
                case "duel":
                    duel(myCommand);
                    break;
                case "duel with ai":
                    duelWithAI(myCommand);
                    break;
            }
            getConfirmation();
        }
    }

    private static void duelWithAI(Command command) {
        String rounds = command.getField("round");
        boolean success = Controller.Menus.DuelMenu.duelWithAI(rounds);
        if (success) {
            print("success");
        } else {
            print(Controller.Menus.DuelMenu.getError());
        }
    }

    private static void duel(Command command) {
        String secondPlayer = command.getField("second-player");
        String rounds = command.getField("round");
        boolean success = Controller.Menus.DuelMenu.duel(secondPlayer , rounds);
        if (success) {
            print("success!");
        } else {
            print(Controller.Menus.DuelMenu.getError());
        }
    }

    private static void initializeMenu() {
        toContinue = true;
        Command.clearValidCommandTypes();
        initializeLogoutCommandType();
        initializeExitCommandType();
        initializeMenuEnterCommandTypes();
        initializeShowCurrentCommandType();
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

    private static void initializeShowCurrentCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("menu show-current");
        commandType.setName("show current menu");
        Command.addCommandType(commandType);
    }

    private static void initializeMenuEnterCommandTypes() {
        initializeProfileMenuEnterCommand();
        initializeDuelMenuEnterCommand();
    }

    private static void initializeDuelMenuEnterCommand() {
        CommandType commandType = new CommandType();
        commandType.setName("enterDuelMenu");
        commandType.setMainPart("menu enter duel");
    }

    private static void initializeProfileMenuEnterCommand() {
        CommandType commandType = new CommandType();
        commandType.setName("enterProfileMenu");
        commandType.setMainPart("menu enter profile");
        Command.addCommandType(commandType);
    }

    private static void initializeExitCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("exit");
        commandType.setMainPart("menu exit");
        Command.addCommandType(commandType);
    }

    private static void initializeLogoutCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("logout");
        commandType.setMainPart("user logout");
        Command.addCommandType(commandType);
    }

}
