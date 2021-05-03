import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profile {
    private Player player;


    public Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }


    public void changeNickname(Matcher matcher) {

    }


    public void changePassword(Matcher matcher) {

    }


    public void enterMenu() {

    }


    public void exitMenu() {

    }


    public void showCurrentMenu() {

    }
}
