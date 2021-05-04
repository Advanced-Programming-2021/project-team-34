import java.util.regex.*;
import java.util.Scanner;

public class Controller {
    public static Matcher matcher;
    public static Matcher matcher1;
    public static Matcher matcher2;
    public static Matcher matcher3;
    public static Matcher matcher4;
    public static Matcher matcher5;
    public static Matcher matcher6;
    public static Scanner scanner = new Scanner(System.in);
    private static String input;

    public static void runLoginMenu() {
        LoginMenu menu = new LoginMenu();
        while (true) {
            input = scanner.nextLine().trim();
            matcher = getMatch(input, "^\\s*user\\s+create\\s+(--username|-u)\\s+(\\w+)\\s+(--password|-p)\\s+(\\w+)\\s+(--nickname|-n)\\s+(\\w+)\\s*$");
            if (matcher.find()) {
                String username = matcher.group(1);
                String password = matcher.group(2);
                String nickname = matcher.group(3);
                new Player(username, password, nickname);
            }
            matcher = getMatch(input, "^\\s*user\\s+create\\s+(--username|-u)\\s+(\\w+)\\s+(--nickname|-n)\\s+(\\w+)\\s+(--password|-p)\\s+(\\w+)\\s*$");
            if (matcher.find()) {
                String username = matcher.group(1);
                String password = matcher.group(3);
                String nickname = matcher.group(2);
                new Player(username, password, nickname);
            }
            matcher = getMatch(input, "^\\s*user\\s+create\\s+(--nickname|-n)\\s+(\\w+)\\s+(--username|-u)\\s+(\\w+)\\s+(--password|-p)\\s+(\\w+)\\s*$");
            if (matcher.find()) {
                String username = matcher.group(2);
                String password = matcher.group(3);
                String nickname = matcher.group(1);
                new Player(username, password, nickname);
            }
            matcher = getMatch(input, "^\\s*user\\s+create\\s+(--nickname|-n)\\s+(\\w+)\\s+(--password|-p)\\s+(\\w+)\\s+(--username|-u)\\s+(\\w+)\\s*$");
            if (matcher.find()) {
                String username = matcher.group(3);
                String password = matcher.group(2);
                String nickname = matcher.group(1);
                new Player(username, password, nickname);
            }
            matcher = getMatch(input, "^\\s*user\\s+create\\s+(--password|-p)\\s+(\\w+)\\s+(--username|-u)\\s+(\\w+)\\s+(--nickname|-n)\\s+(\\w+)\\s*$");
            if (matcher.find()) {
                String username = matcher.group(2);
                String password = matcher.group(1);
                String nickname = matcher.group(3);
                new Player(username, password, nickname);
            }
            matcher = getMatch(input, "^\\s*user\\s+create\\s+(--password|-p)\\s+(\\w+)\\s+(--nickname|-n)\\s+(\\w+)\\s+(--username|-u)\\s+(\\w+)\\s*$");
            if (matcher.find()) {
                String username = matcher.group(3);
                String password = matcher.group(1);
                String nickname = matcher.group(2);
                new Player(username, password, nickname);
            }
        }
    }


    public static Matcher getMatch(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }
}
