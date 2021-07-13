package View.Menus;

import Controller.MenuController;
import Controller.MenuNames;
import View.CommandHelper.Command;
import View.CommandHelper.CommandType;

public class StartMenu extends ViewMenu {
    static boolean toContinue = true;
    public static void run() {
        initializeMenu();
        doMain();
    }

    protected static void doMain() {
        String input;
        Command myCommand;
        String typeOfMyCommand;
        while (toContinue) {
            show("LOGIN MENU");
            input = input();
            myCommand = new Command(input);
            typeOfMyCommand = myCommand.getType();
            switch (typeOfMyCommand) {
                case "invalid type" :
                    print("The command you inputted is not valid in this menu!!!");
                    break;
                case "login":
                    login(myCommand);
                    break;
                case "exit":
                    MenuController.setMenuName(MenuNames.Exit);
                    toContinue = false;
                    break;
                case "show current menu":
                    print("Login Menu");
                    break;
                case "create user":
                    createUser(myCommand);
                    break;
            }
            getConfirmation();
        }
    }

    private static void login(Command myCommand) {
        boolean success = Controller.Menus.StartMenu.login(myCommand.getField("username"),
                myCommand.getField("password"));
        if (success) {
            print("You logged in successfully");
            toContinue = false;
            // in controller : MenuController.setMenuName(MenuNames.MainMenu);
        } else {
            print(Controller.Menus.StartMenu.getError());
        }
    }

    private static void createUser(Command myCommand) {
        boolean success = Controller.Menus.StartMenu.register(myCommand.getField("username"),
                myCommand.getField("nickname"),
                myCommand.getField("password"));
        if (success) {
            print("user created successfully");
        } else {
            print(Controller.Menus.StartMenu.getError());
        }
    }

    private static void initializeMenu() {
        toContinue = true;
        Command.clearValidCommandTypes();
        initializeLoginCommandType();
        initializeExitCommandType();
        initializeShowCurrentMenuCommandType();
        initializeCreateUserCommandType();
    }

    private static void initializeCreateUserCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("create user");
        commandType.setMainPart("user create");
        commandType.addField("username");
        commandType.addField("password");
        commandType.addField("nickname");
        Command.addCommandType(commandType);
    }

    private static void initializeShowCurrentMenuCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("menu show-current");
        commandType.setName("show current menu");
        Command.addCommandType(commandType);
    }

    private static void initializeExitCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("exit");
        commandType.setMainPart("menu exit");
        Command.addCommandType(commandType);
    }

    private static void initializeLoginCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("user login");
        commandType.setName("login");
        commandType.addField("username");
        commandType.addField("password");
        Command.addCommandType(commandType);
    }

}
