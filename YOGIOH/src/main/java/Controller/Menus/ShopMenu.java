package Controller.Menus;


import Model.Card;
import Model.User;

import java.util.Objects;


public class ShopMenu {

    public String buyCard(String cardName, String username) {
        if (!Card.getAllCards().containsKey(cardName)) {
            return "there is no card with this name";
        }
        Card card = Card.getAllCards().get(cardName);
        if (Objects.requireNonNull(User.getUserByUsername(username)).doesHaveEnoughCoin(card.getPrice())) {
            return "not enough money";
        }
        Objects.requireNonNull(User.getUserByUsername(username)).increaseCoin(-card.getPrice());
        Objects.requireNonNull(User.getUserByUsername(username)).addCard(card);
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