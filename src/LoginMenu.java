import java.util.regex.*;

public class LoginMenu {

    public Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }

    public final void createUser(Matcher matcher) {
        // \\s*user\\s+creat\\s*username
    }

    public final void login(Matcher matcher) {

    }

    public final void menuExit() {

    }

    public final void showCurrentMenu() {

    }
}
