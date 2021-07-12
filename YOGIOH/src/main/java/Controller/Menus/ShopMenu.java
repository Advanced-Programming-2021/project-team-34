package Controller.Menus;


import Model.Card;
import Model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;


public class ShopMenu {
    static String error;

    public static String getError() {
        return error;
    }


    public static boolean buyCard(String nameOfCardToBuy) {
        return false;// TODO : ...
    }

    // TODO : probably to delete the method below
    public String buyCard(String cardName, String username) throws IOException {
        if (!Card.getAllCards().containsKey(cardName)) {
            return "there is no card with this name";
        }
        Card card = Card.getAllCards().get(cardName);
        if (Objects.requireNonNull(User.getUserByUsername(username)).doesHaveEnoughCoin(card.getPrice())) {
            return "not enough money";
        }
        Objects.requireNonNull(User.getUserByUsername(username)).increaseCoin(-card.getPrice());
        Objects.requireNonNull(User.getUserByUsername(username)).addCard(card);
        FileWriter writer = new FileWriter("src\\main\\resources\\data\\" + username + "cards.txt");
        writer.append(cardName).append("\n");
        writer.close();
        return "shop completed";
    }

    //show command for a specific card
    public String cardShow(String cardName) {
        if (cardName != null){
            if (Card.getAllCards().containsKey(cardName)){
                return Card.getAllCards().get(cardName).toString();
            }
            return "card with this name, could not be found!";
        }
        return "invalid command";
    }
}
