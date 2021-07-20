package Controller.Menus;


import Controller.MenuController;
import Exceptions.NoMonsterWithThisNameException;
import Model.Card;
import Model.Monster;
import Model.SpellAndTrap;
import Model.User;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;


public class ShopMenu {
    static String error;

    public static String getError() {
        return error;
    }


    public static boolean buyCard(String nameOfCardToBuy) {
        String result = buyCard(nameOfCardToBuy , MenuController.getLoggedInUser().getUsername());
        if (result.equals("shop completed")) {
            return true;
        } else {
            error = result;
            return false;
        }
    }

    public static String buyCard(String cardName, String username) {
        try {
            if (!Card.getNameOfAllCardsInAlphabeticalOrder().contains(cardName)) {
                return "there is no card with this name";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Card card = null;
        try {
            card = new Monster(cardName);
        } catch (NoMonsterWithThisNameException e) {
            try {
                card = new SpellAndTrap(cardName);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!(User.getUserByUsername(username)).doesHaveEnoughCoin(card.getPrice())) {
            //System.out.println("card price : "+card.getPrice()+" , but you have only "+User.getUserByUsername(username).getCoin());
            return "not enough money";
        }
        if ((User.getUserByUsername(username).getACardWithName(cardName)) != null) {
            return "you already have this card";
        }
        Objects.requireNonNull(User.getUserByUsername(username)).increaseCoin(-card.getPrice());
        if (User.getUserByUsername(username).getCards().containsKey(card)) {
            User.getUserByUsername(username).getCards().put(card, User.getUserByUsername(username).getCards().get(card) + 1);
        } else {
            User.getUserByUsername(username).getCards().put(card, 1);
        }
        try {
            FileWriter writer = new FileWriter("src\\main\\resources\\data\\" + username + "cards.txt");
            writer.append(cardName).append("\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("could not save bought card");
            e.printStackTrace();
        }
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
