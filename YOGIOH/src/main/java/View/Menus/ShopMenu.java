package View.Menus;

import Controller.MenuController;
import Controller.MenuNames;
import View.CommandHelper.Command;
import View.CommandHelper.CommandType;

public class ShopMenu extends ViewMenu {
    static boolean toContinue = true;
    public static void run() {
        initializeMenu();
        doMain();
    }

    private static void doMain() {
        String input;
        Command myCommand;
        String typeOfMyCommand;
        while (toContinue) {
            show("SHOP MENU");
            input = input();
            myCommand = new Command(input);
            typeOfMyCommand = myCommand.getType();
            switch (typeOfMyCommand) {
                case "invalid type" :
                    print("The command you inputted is not valid in this menu!!!");
                    break;
                case "exit":
                    MenuController.setMenuName(MenuNames.Exit);
                    toContinue = false;
                    break;
                case "show current menu":
                    print("Shop Menu");
                    break;
            }
            getConfirmation();
        }
    }

    private static void initializeMenu() {
        toContinue = true;
        Command.clearValidCommandTypes();
        initializeExitCommandType();
        initializeShowCurrentMenuCommandType();
        initializeBuyCardCommandType();
        initializeShowAllCommandType();
    }

    private static void initializeShowCurrentMenuCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("show current menu");
        commandType.setMainPart("menu show-current");
        Command.addCommandType(commandType);
    }

    private static void initializeExitCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("menu exit");
        commandType.setName("exit");
        Command.addCommandType(commandType);
    }

    private static void initializeShowAllCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("show all");
        commandType.setMainPart("shop show all");
        Command.addCommandType(commandType);
    }

    private static void initializeBuyCardCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("buy card");
        commandType.setMainPart("shop");
        commandType.getField("buy");
        Command.addCommandType(commandType);
    }


}
