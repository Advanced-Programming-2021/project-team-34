import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShoppingMenu {
    private Player currentUser;
    private static ShoppingMenu shopMenu = null;

    private ShoppingMenu(Player newUser) {
        currentUser = newUser;
    }

    public static ShoppingMenu getInstance(Player newUser) {
        if (shopMenu == null) shopMenu = new ShoppingMenu(newUser);
        return shopMenu;
    }

    public void runShoppingMenu(Player currentUser) {
        Scanner userCommand = new Scanner(System.in);

        while (UserAndMenuController.currentMenu == Menus.SHOP_MENU) {
            String inputCommand = userCommand.nextLine().trim();
            if (inputCommand.startsWith("card show")) ShowCard.showSelectedCard(inputCommand);
            else if (inputCommand.equals("shop show --all")) showAllCards();
            else if (inputCommand.equals("menu enter")) System.out.println("menu navigation is not possible");
            else if (inputCommand.equals("menu show-current")) System.out.println("Shop Menu");
            else if (inputCommand.equals("menu exit")) UserAndMenuController.currentMenu = Menus.MAIN_MENU;
            else if (inputCommand.startsWith("shop buy")) buyDemandedCard(inputCommand);
            else System.out.println("invalid command");
        }
    }

    private void buyDemandedCard(String inputCommand) {
        Pattern pattern = Pattern.compile("^shop buy (.+)$");
        Matcher matcher = pattern.matcher(inputCommand);
        if (matcher.find()){
            String cardName = matcher.group(1);
            if (!Card.allCards.containsKey(cardName)) System.out.println("there is no card with this name");
            else {
                if (Card.allCards.get(cardName).getPrice() > currentUser.getCoin())
                    System.out.println("not enough money");
                else{
                    ArrayList<String> temporarilyCards = currentUser.getCards();
                    temporarilyCards.add(cardName);
                    currentUser.setCards(temporarilyCards);
                    currentUser.decreaseCoins(Card.allCards.get(cardName).getPrice());
                    System.out.println("card bought successfully");
                }
            }
        }
        else System.out.println("invalid command");
    }

    private void showAllCards() {
        ArrayList<String> allCards = new ArrayList<>();

        //save names of cards in an arrayList
        for (Map.Entry<String,Card> names : Card.allCards.entrySet()) allCards.add(names.getKey());

        Collections.sort(allCards);

        for (String card : allCards) {
            System.out.println(card + ":" + Card.allCards.get(card).getPrice());
        }
    }


}
