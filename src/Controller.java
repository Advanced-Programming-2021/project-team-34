import java.util.regex.*;
import java.util.Scanner;

public class Controller {

    public static Scanner scanner = new Scanner(System.in);
    public static Matcher matcher;
    private static String input;
    private static boolean flag = false;

    public static void runLoginMenu() {
        while (true) {
            flag = false;
            input = scanner.nextLine().trim();
            createUser();
            if (!flag) loginUser();
            if (!flag && enterMenu()) {
                flag = true;
                LoginMenu.menuEnter(matcher.group(1));
            }
            if (!flag && menuShowCurrent()) {
                flag = true;
                LoginMenu.showCurrentMenu();
            }
            if (!flag && exitMenu()) {
                flag = true;
                break;
            }
            if (!flag) System.out.println("invalid command");
        }
    }

    private static void createUser() {
        matcher = getMatch(input, "^user create (--username|-u) (\\w+) (--password|-p) (\\w+) (--nickname|-n) (\\w+)$");
        if (matcher.find()) {
            flag = true;
            LoginMenu.createUser(matcher.group(2), matcher.group(4), matcher.group(6));
        }
        matcher = getMatch(input, "^user create (--username|-u) (\\w+) (--nickname|-n) (\\w+) (--password|-p) (\\w+)$");
        if (matcher.find()) {
            flag = true;
            LoginMenu.createUser(matcher.group(2), matcher.group(6), matcher.group(4));
        }
        matcher = getMatch(input, "^user create (--nickname|-n) (\\w+) (--username|-u) (\\w+) (--password|-p) (\\w+)$");
        if (matcher.find()) {
            flag = true;
            LoginMenu.createUser(matcher.group(4), matcher.group(6), matcher.group(2));
        }
        matcher = getMatch(input, "^user create (--nickname|-n) (\\w+) (--password|-p) (\\w+) (--username|-u) (\\w+)$");
        if (matcher.find()) {
            flag = true;
            LoginMenu.createUser(matcher.group(6), matcher.group(4), matcher.group(2));
        }
        matcher = getMatch(input, "^user create (--password|-p) (\\w+) (--username|-u) (\\w+) (--nickname|-n) (\\w+)$");
        if (matcher.find()) {
            flag = true;
            LoginMenu.createUser(matcher.group(4), matcher.group(2), matcher.group(6));
        }
        matcher = getMatch(input, "^user create (--password|-p) (\\w+) (--nickname|-n) (\\w+) (--username|-u) (\\w+)$");
        if (matcher.find()) {
            flag = true;
            LoginMenu.createUser(matcher.group(6), matcher.group(2), matcher.group(4));
        }
    }

    private static void loginUser() {
        matcher = getMatch(input, "^user login (--password|-p) (\\w+) (--username|-u) (\\w+)$");
        if (matcher.find()) {
            flag = true;
            LoginMenu.login(matcher.group(4), matcher.group(2));
        }
        matcher = getMatch(input, "^user login (--username|-u) (\\w+) (--password|-p) (\\w+)$");
        if (matcher.find()) {
            flag = true;
            LoginMenu.login(matcher.group(2), matcher.group(4));
        }
    }

    private static boolean enterMenu() {
        matcher = getMatch(input, "^menu enter (\\S+)$");
        return matcher.find();
    }

    private static boolean exitMenu() {
        matcher = getMatch(input, "^menu exit$");
        return matcher.find();
    }

    private static boolean menuShowCurrent() {
        matcher = getMatch(input, "^menu show-current$");
        return matcher.find();
    }

    public static Matcher getMatch(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}