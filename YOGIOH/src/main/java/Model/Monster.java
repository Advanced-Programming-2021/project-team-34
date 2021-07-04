package Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Monster extends Card {
    private int level;
    private String monsterType;
    private int defaultAttPower;//default attack power
    private int defaultDefPower;//default defence power
    public String Ritual;
    private int price;
    private ArrayList<Monster> monsters;
    private String monsterName;
    private String attribute;

    //TO DO :
    /* To Add Constructor */
    //!!!!!!!!!!!!!!!!!!!!!!
    //TO DO :
    /* To Add All Methods */
    public Monster(String monsterName) throws IOException {
        super(monsterName);
        super.typeOfCard = TypeOfCard.MONSTER;
        String text = "";
        File file = new File("src/main/resources/Monster.csv");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text = scanner.nextLine();
            Pattern pattern = Pattern.compile(monsterName + ",(\\d+),(\\S+?),(\\S+?),(\\S+?),(\\d+),(\\d+),(.+?)," +
                    "(\\d+)");
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                setLevel(Integer.parseInt(matcher.group(1)));
                attribute = matcher.group(2);
                setMonsterType(matcher.group(3));
                super.type = matcher.group(4);
                setDefAttPower(Integer.parseInt(matcher.group(5)));
                setDefDefPower(Integer.parseInt(matcher.group(6)));
                setDescription(matcher.group(7));
                setPrice(Integer.parseInt(matcher.group(8)));
            }
        }
    }


    public void setPrice(int price) {
        this.price = price;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMonsterType() {
        return this.monsterType;
    }

    public void setMonsterType(String monsterType) {
        this.monsterType = monsterType;
    }

    public int getDefAttPower() {
        return this.defaultAttPower;
    }

    public void setDefAttPower(int defAttPower) {
        defaultAttPower = defAttPower;
    }

    public int getDefDefPower() {
        return this.defaultDefPower;
    }

    public void setDefDefPower(int defDefPower) {
        defaultDefPower = defDefPower;
    }

    public String getRitual() {
        return this.Ritual;
    }

    public void setRitual(String Ritual) {
        this.Ritual = Ritual;
    }

    public String getMonsterName() {
        return this.monsterName;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }

    public String getAttribute() {
        return attribute;
    }
}
