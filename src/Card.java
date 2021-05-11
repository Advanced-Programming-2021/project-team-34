import java.util.ArrayList;
import java.util.HashMap;

public abstract class Card {
    protected String name;
    protected String type;
    protected String description;
    protected Player owner;
    protected static HashMap<String, Card> allCards;
    private int price;

    public Card(int price, String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        if (!allCards.containsKey(name)) allCards.put(name, this);
    }

    public abstract String getName() {
        return this.name;
    }

    public abstract void setName(String name) {
        this.name = name;
    }

    public abstract String getType() {
        return this.type;
    }

    public abstract void setType(String type) {
        this.type = type;
    }

    public abstract String getDescription() {
        return this.description;
    }

    public abstract void setDescription(String description) {
        this.description = description;
    }

    public abstract Player getOwner() {
        return this.owner;
    }

    public abstract void setOwner(Player owner) {
        this.owner = owner;
    }
    public abstract void show() {
    }

    public int getPrice() {
        return this.price;
    }
}