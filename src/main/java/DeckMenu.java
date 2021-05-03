import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeckMenu {
    private Player player;


    public void getPlayer() {

    }


    public void enterMenu(Matcher matcher) {

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


    public void createDeck(Matcher matcher) {

    }


    public void deleteDeck(Matcher matcher) {

    }


    public void activateDeck(Matcher matcher) {

    }


    public void addCardToDeck(Matcher matcher) {

    }


    public void removeCardFromDeck(Matcher matcher) {

    }


    public void showDecks() {

    }


    public void showDeck(Matcher matcher) {

    }


    public void showCards(Matcher matcher) {

    }
}
