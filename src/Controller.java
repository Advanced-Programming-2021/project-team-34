import java.util.regex.*;
import java.util.Scanner;

public class Controller {

    public static Scanner scanner = new Scanner(System.in);
    public static Matcher matcher;
    private static String input;
    private static boolean flag = false;

    public static void runLoginMenu() {
        while (true) {
            input = scanner.nextLine().trim();
            createUser();
            if (!flag)loginUser();
            if (!flag) {
                matcher = getMatch(input, "^\\s*menu\\s+enter\\s+(LoginMenu|MainMenu|DuelMenu|DeckMenu|" +
                        "ScoreboardMenu|ProfileMenu|ShopMenu|Import\\/ExportMenu)\\s*$");
                if (matcher.find()) {
                    flag = true;
                    LoginMenu.menuEnter(matcher.group(1));
                }
            }
        }
    }

    private static void createUser() {
        matcher = getMatch(input, "^\\s*user\\s+create\\s+(--username|-u)\\s+(\\w+)\\s+(--password|-p)\\s+" +
                "(\\w+)\\s+(--nickname|-n)\\s+(\\w+)\\s*$");
        if (matcher.find()) {
            flag = true;
            LoginMenu.createUser(matcher.group(2), matcher.group(4), matcher.group(6));
        }
        matcher = getMatch(input, "^\\s*user\\s+create\\s+(--username|-u)\\s+(\\w+)\\s+(--nickname|-n)\\s+" +
                "(\\w+)\\s+(--password|-p)\\s+(\\w+)\\s*$");
        if (matcher.find()) {
            flag = true;
            LoginMenu.createUser(matcher.group(2), matcher.group(6), matcher.group(4));
        }
        matcher = getMatch(input, "^\\s*user\\s+create\\s+(--nickname|-n)\\s+(\\w+)\\s+(--username|-u)\\s+" +
                "(\\w+)\\s+(--password|-p)\\s+(\\w+)\\s*$");
        if (matcher.find()) {
            flag = true;
            LoginMenu.createUser(matcher.group(4), matcher.group(6), matcher.group(2));
        }
        matcher = getMatch(input, "^\\s*user\\s+create\\s+(--nickname|-n)\\s+(\\w+)\\s+(--password|-p)\\s+" +
                "(\\w+)\\s+(--username|-u)\\s+(\\w+)\\s*$");
        if (matcher.find()) {
            flag = true;
            LoginMenu.createUser(matcher.group(6), matcher.group(4), matcher.group(2));
        }
        matcher = getMatch(input, "^\\s*user\\s+create\\s+(--password|-p)\\s+(\\w+)\\s+(--username|-u)\\s+" +
                "(\\w+)\\s+(--nickname|-n)\\s+(\\w+)\\s*$");
        if (matcher.find()) {
            flag = true;
            LoginMenu.createUser(matcher.group(4), matcher.group(2), matcher.group(6));
        }
        matcher = getMatch(input, "^\\s*user\\s+create\\s+(--password|-p)\\s+(\\w+)\\s+(--nickname|-n)\\s+" +
                "(\\w+)\\s+(--username|-u)\\s+(\\w+)\\s*$");
        if (matcher.find()) {
            flag = true;
            LoginMenu.createUser(matcher.group(6), matcher.group(2), matcher.group(4));
        }
    }

    private static void loginUser() {
        matcher = getMatch(input, "^\\s*user\\s+login\\s+(--password|-p)\\s+(\\w+)\\s+(--username|-u)\\s+(\\w+)" +
                "\\s*$");
        if (matcher.find()) {
            flag = true;
            LoginMenu.login(matcher.group(4), matcher.group(2));
        }
        matcher = getMatch(input, "^\\s*user\\s+login\\s+(--username|-u)\\s+(\\w+)\\s+(--password|-p)\\s+(\\w+)" +
                "\\s*$");
        if (matcher.find()) {
            flag = true;
            LoginMenu.login(matcher.group(2), matcher.group(4));
        }
    }

    public static void runMainMenu(Player loginned) {

    }


    public static Matcher getMatch(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
