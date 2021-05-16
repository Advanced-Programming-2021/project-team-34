import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;

public class DecksMenu {
    private Player currentUser;
    private static DecksMenu deckMenu = null;


    public static DecksMenu getInstance(Player newUser) {
        if (deckMenu == null) deckMenu = new DecksMenu();
        deckMenu.currentUser = newUser;
        return deckMenu;
    }

    public void runDeckMenu() {

        Scanner scanner = new Scanner(System.in);

        while (UserAndMenuController.currentMenu == Menus.DECK_MENU){

            String userCommand = scanner.nextLine().trim();
            Matcher matcher;

            if (userCommand.equals("menu show-current")) System.out.println("Deck Menu");
            else if (userCommand.equals("menu exit")) UserAndMenuController.currentMenu = Menus.MAIN_MENU;
            else if (userCommand.startsWith("menu enter")) System.out.println("navigation is not possible");
            else if (userCommand.startsWith("card show"))ShowCard.showSelectedCard(userCommand);
            else if (userCommand.startsWith("deck create")){
                if ((matcher = Controller.getMatch(userCommand, "deck create (.*)")).find())
                    currentUser.createDeck(matcher.group(1));
            }
            else if (userCommand.startsWith("deck delete")){
                if((matcher = Controller.getMatch(userCommand,"deck delete (.*)")).find())
                    currentUser.deleteDeck(matcher.group(1));
            }
            else if (userCommand.startsWith("deck set-active")){
                if ((matcher = Controller.getMatch(userCommand, "deck set-active (\\S+)")).find())ActiveTheChosenDeck(matcher.group(1));
            }
            else if (userCommand.startsWith("deck add-card")){
                if ((matcher = Controller.getMatch(userCommand, "^deck add-card --card (.+) --deck (.+) --side$")).find())addCardToSideDeck(matcher);
                else if ((matcher = Controller.getMatch(userCommand, "^deck add-card --card (.+) --deck (.+)$")).find())addCardToMainDeck(matcher);
            }

        }

    }

    private void addCardToSideDeck(Matcher matcher) {
        String cardName = matcher.group(1) ,deckName = matcher.group(2);

        if(currentUser.getDeckByName(deckName) == null ) System.out.println("deck with name " + deckName + " does not exists");

    }

    private void addCardToMainDeck(Matcher matcher) {

    }

    private void ActiveTheChosenDeck(String deckNameToActive) {
        for(Deck deck : currentUser.decks){
            if (deck.getName().equals(deckNameToActive)){
                currentUser.setActiveDeck(deck);
                System.out.println("deck activated successfully");
                return;
            }
        }
        System.out.println("deck with name " + deckNameToActive + " does not exist");
    }

}


