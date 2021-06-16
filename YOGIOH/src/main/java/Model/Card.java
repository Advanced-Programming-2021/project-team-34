package Model;

import java.util.HashMap;

public abstract class Card {
    protected String name;
    protected String type;
    protected String description;
//    protected Player owner;
    protected static HashMap<String, Card> allCards;
    private int price;


    public Card(String name) {
        setName(name);
        if (!allCards.containsKey(name)) allCards.put(name, this);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
//
//    public Player getOwner() {
//        return this.owner;
//    }
//
//    public void setOwner(Player owner) {
//        this.owner = owner;
//    }

    public int getPrice() {
        return this.price;
    }
}
