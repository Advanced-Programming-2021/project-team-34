import java.util.regex.*;

public class LoginMenu {

    public Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

    public void createUser(Matcher matcher) {
        // \\s*user\\s+creat\\s*username
    }

    public void login(Matcher matcher) {

    }

    public void menuExit() {

    }

    public void showCurrentMenu() {

    }
}
