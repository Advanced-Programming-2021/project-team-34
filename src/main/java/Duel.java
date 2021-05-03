import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duel {
    private Player player;


    public void enterMenu() {

    }


    public void exitMenu() {

    }


    public void showCurrentMenu() {

    }


    public Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }


    public void showCard(Matcher matcher) {

    }


    public void duel(Matcher matcher) {

    }
}
