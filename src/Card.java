import java.util.ArrayList;
public class Card {
    private String name;
    private String type;
    private String description;
    private Player owner;
    private static ArrayList<Card> allCards;

    public Card(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
        allCards.add(this);
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
    
    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

}