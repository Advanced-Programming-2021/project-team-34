import java.util.Scanner;

public class ProfileMenu {
    private static Player currentUser;
    private static Scanner scanner;

    public static void runProfileMenu(Player newUser) {

        currentUser = newUser;
        String userCommand = scanner.nextLine().trim();

        while (true) {
            if (userCommand.startsWith("Menu enter"))
        }

    }
}
