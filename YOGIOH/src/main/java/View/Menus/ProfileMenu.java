package View.Menus;

import Controller.MenuController;
import Controller.MenuNames;
import View.CommandHelper.Command;
import View.CommandHelper.CommandType;

public class ProfileMenu extends ViewMenu {
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
            show("PROFILE MENU");
            input = input();
            myCommand = new Command(input);
            typeOfMyCommand = myCommand.getType();
            switch (typeOfMyCommand) {
                case "invalid type":
                    print("The command you inputted is not valid in this menu!!!");
                    break;
                case "exit":
                    MenuController.setMenuName(MenuNames.MainMenu);
                    toContinue = false;
                    break;
                case "show current menu":
                    print("Profile Menu");
                    break;
                case "change nickname" :
                    changeNickname(myCommand);
                    break;
                case "change password":
                    changePassword(myCommand);
                    break;
            }
            getConfirmation();
        }
    }

    private static void changePassword(Command myCommand) {
        String currentPassword = myCommand.getField("current");
        String newPassword = myCommand.getField("new");
        boolean success = Controller.Menus.ProfileMenu.changePassword(currentPassword, newPassword);
        if (success) {
            print("password changed successfully");
        } else {
            print(Controller.Menus.ProfileMenu.getError());
        }
    }

    private static void changeNickname(Command myCommand) {
        String nickname = myCommand.getField("nickname");
        boolean success = Controller.Menus.ProfileMenu.changeNickname(nickname);
        if (success) {
            print("nickname changed successfully");
        } else {
            print(Controller.Menus.ProfileMenu.getError());
        }
    }


    private static void initializeMenu() {
        toContinue = true;
        Command.clearValidCommandTypes();
        initializeExitCommandType();
        initializeChangeNicknameCommandType();
        initializeChangePasswordCommandType();
        initializeShowCurrentMenuCommandType();

    }

    private static void initializeShowCurrentMenuCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("menu show-current");
        commandType.setName("show current menu");
        Command.addCommandType(commandType);
    }

    private static void initializeChangePasswordCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("change password");
        commandType.setMainPart("profile change");
        commandType.addField("password");
        commandType.getField("password").setDoesHaveArgument(false);
        commandType.addField("current");
        commandType.addField("new");
        Command.addCommandType(commandType);
    }

    private static void initializeChangeNicknameCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("profile change");
        commandType.setName("change nickname");
        commandType.addField("nickname");
        Command.addCommandType(commandType);
    }

    private static void initializeExitCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("exit");
        commandType.setMainPart("menu exit");
        Command.addCommandType(commandType);
    }
}
