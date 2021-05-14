import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenu {
    private static Player currentUser;
    private static Scanner scanner = new Scanner(System.in);

    public static void runProfileMenu(Player newUser) {

        currentUser = newUser;
        String userCommand = scanner.nextLine().trim();

        while (true) {
            if (userCommand.startsWith("Menu enter")) MainMenu.runChangingMenu(userCommand, currentUser);
            else if (userCommand.startsWith("profile change --nickname")) changingNickName(userCommand);
            else if (userCommand.equals("profile change --password")) changingPassword(userCommand);
            else if (userCommand.equals("menu show-current")) System.out.println("Profile Menu");
            else if (userCommand.equals("menu exit")) {
                UserAndMenuController.currentMenu = Menus.MAIN_MENU;
                break;
            } else System.out.println("invalid command");
        }

    }

    public static void changingPassword(String userCommand) {
        Matcher matcher = Controller.getMatch(userCommand, "^profile change --password --current (\\S+) --new (\\S+)$");
        if (matcher.find()) {
            String currentPassword = matcher.group(1);
            String newPassword = matcher.group(2);
            if (!currentUser.checkPassword(currentPassword)) System.out.println("current password is invalid");
            else if (currentUser.checkPassword(newPassword)) System.out.println("please enter new password");
            else {
                currentUser.setPassword(newPassword);
                System.out.println("password changed successfully");
            }
        } else System.out.println("invalid command");

    }

    public static void changingNickName(String userCommand) {
        Matcher matcher = Controller.getMatch(userCommand, "^profile change --nickname (\\S+)$");
        if (matcher.find()){
            String newNickname = matcher.group(1);
            currentUser.setNickname(newNickname);
        }
        else System.out.println("invalid command");
    }
}
