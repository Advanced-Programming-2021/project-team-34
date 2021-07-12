package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public abstract class Card {
    protected String name;
    protected String type;
    TypeOfCard typeOfCard = TypeOfCard.NOT_DETERMINED;
    protected String description;
    //    protected Player owner;
    protected static HashMap<String, Card> allCards;
    private int price;

    static {
        allCards = new HashMap<>();
    }


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

    public static HashMap<String, Card> getAllCards() { return allCards; }

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

    public TypeOfCard getTypeOfCard() {
        return typeOfCard;
    }

    public static ArrayList<String> getNameOfAllMonsters() throws FileNotFoundException {
        File file = new File("src/main/resources/Monster.csv");
        Scanner scanner = new Scanner(file);
        scanner.nextLine();
        String name;
        ArrayList<String> answer = new ArrayList<String>();
        while (scanner.hasNextLine()) {
            answer.add(scanner.nextLine().split("\\s*,")[0]);
        }
        return answer;
    }

    public static ArrayList<String> getNameOfAllSpellsAndTraps() throws FileNotFoundException {
        File file = new File("src/main/resources/SpellTrap.csv");
        Scanner scanner = new Scanner(file);
        scanner.nextLine();
        String name;
        ArrayList<String> answer = new ArrayList<String>();
        while (scanner.hasNextLine()) {
            answer.add(scanner.nextLine().split("\\s*,")[0]);
        }
        return answer;
    }

    public static ArrayList<String> getNameOfAllCardsInAlphabeticalOrder() throws FileNotFoundException {
        ArrayList<String> answer = new ArrayList<String>();
        answer.addAll(getNameOfAllSpellsAndTraps());
        answer.addAll(getNameOfAllMonsters());
        answer.sort(String::compareToIgnoreCase);
        return answer;
    }
}

