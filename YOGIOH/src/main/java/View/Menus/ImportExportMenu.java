package View.Menus;

import Controller.MenuController;
import Controller.MenuNames;
import View.CommandHelper.Command;
import View.CommandHelper.CommandType;

public class ImportExportMenu extends ViewMenu {
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
            show("IMPORT/EXPORT MENU");
            input = input();
            myCommand = new Command(input);
            typeOfMyCommand = myCommand.getType();
            switch (typeOfMyCommand) {
                case "invalid type" :
                    print("The command you inputted is not valid in this menu!!!");
                    break;
                case "exit":
                    MenuController.setMenuName(MenuNames.MainMenu);
                    toContinue = false;
                    break;
                case "show current menu":
                    print("Import/Export Menu");
                    break;
                case "import":
                    importCard(myCommand);
                    break;
                case "export":
                    exportCard(myCommand);
                    break;

            }
            getConfirmation();
        }
    }

    private static void exportCard(Command command) {
        String cardName = command.getField("card");
        boolean success = Controller.Menus.ImportExportMenu.exportCard(cardName);
        if (success) {
            print("card exported successfully");
        } else {
            print(Controller.Menus.ImportExportMenu.getError());
        }
    }

    private static void importCard(Command command) {
        String cardName = command.getField("card");
        boolean success = Controller.Menus.ImportExportMenu.importCard(cardName);
        if (success) {
            print("card imported successfully");
        } else {
            print(Controller.Menus.ImportExportMenu.getError());
        }
    }

    private static void initializeMenu() {
        toContinue = true;
        Command.clearValidCommandTypes();
        initializeExitCommandType();
        initializeShowCurrentMenuCommandType();
        initializeImportCommandType();
        initializeExportCommandType();
    }

    private static void initializeExportCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("export");
        commandType.setName("export");
        commandType.addField("card");
        Command.addCommandType(commandType);
    }

    private static void initializeImportCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("import");
        commandType.setMainPart("import");
        commandType.addField("card");
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
}
