package View.Menus;
import Controller.MenuController;
import Controller.MenuNames;
import Model.User;
import View.CommandHelper.Command;
import View.CommandHelper.CommandType;

import java.util.Comparator;

public class ScoreBoardMenu extends ViewMenu {
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
            show("SCORE BOARD MENU");
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
                    print("ScoreBoard Menu");
                    break;
                case "show":
                    showScoreBoard();
                    break;
            }
            getConfirmation();
        }
    }

    private static void showScoreBoard() {
        User.users.sort(new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
                int c = Integer.compare(t1.getHighScore() , user.getHighScore());
                if (c!=0) {
                    return c;
                } else {
                    return String.CASE_INSENSITIVE_ORDER.compare(t1.getNickname() , user.getNickname());
                }
            }
        });
        for (User user :
                User.users) {
            print(user.getRank() + "- " + user.getNickname() + ": " + user.getHighScore());
        }
    }

    private static void initializeMenu() {
        toContinue = true;
        Command.clearValidCommandTypes();
        initializeExitCommandType();
        initializeShowCurrentMenuCommandType();
        initializeShowCommandType();
    }

    private static void initializeShowCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("scoreboard show");
        commandType.setName("show");
        Command.addCommandType(commandType);
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
}
