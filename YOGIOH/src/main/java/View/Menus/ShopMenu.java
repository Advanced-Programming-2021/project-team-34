package View.Menus;

import Controller.MenuController;
import Controller.MenuNames;
import View.CommandHelper.Command;
import View.CommandHelper.CommandType;

import java.util.ArrayList;

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
                case "show all":
                    showAll();
                    break;
                case "buy card":
                    buy(myCommand);
                    break;
            }
            getConfirmation();
        }
    }

    private static void buy(Command command) {
        String nameOfCardToBuy = command.getField("buy");
        boolean success = Controller.Menus.ShopMenu.buyCard(nameOfCardToBuy);
        if (success) {
            print("card bought successfully");
        } else {
            print(Controller.Menus.ShopMenu.getError());
        }

    }

    private static void showAll() {
        // TODO : @aliagha do this
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
