import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu {
    private Player player;


    public Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }


    public void enterMenu(Matcher matcher) {

    }


    public void exitMenu() {

    }


    public void showCurrentMenu() {

    }


    public void logout() {

    }


    public void getPlayer() {

    }
}