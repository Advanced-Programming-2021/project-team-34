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
            }
            getConfirmation();
        }
    }

    private static void initializeMenu() {
        toContinue = true;
        Command.clearValidCommandTypes();
        initializeLogoutCommandType();
        initializeExitCommandType();
        initializeMenuEnterCommandTypes();
        initializeShowCurrentCommandType();
    }

    private static void initializeShowCurrentCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("menu show-current");
        commandType.setName("show current menu");
        Command.addCommandType(commandType);
    }

    private static void initializeMenuEnterCommandTypes() {
        initializeProfileMenuEnterCommand();
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
