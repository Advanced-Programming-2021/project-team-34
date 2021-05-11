import java.util.ArrayList;

public abstract class Card {
    protected String name;
    protected String type;
    protected String description;
    protected Player owner;
    public static ArrayList<Card> allCards;

    public Card(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
        allCards.add(this);
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

}