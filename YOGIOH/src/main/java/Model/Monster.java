package Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Monster extends Card {
    private int attPower;//attack power
    private int defPower;//defence power
    private int level;
    private String state;
    private int defaultAttPower;//default attack power
    private int defaultDefPower;//default defence power
    public String Ritual;
    private int price;
    private ArrayList<Monster> monsters;
    private String monsterName;
    private int timesAffected = 0;

    //TO DO :
    /* To Add Constructor */
    //!!!!!!!!!!!!!!!!!!!!!!
    //TO DO :
    /* To Add All Methods */
    public Monster(String monsterName) throws IOException {
        super(monsterName);
        String text = "";
        File file = new File("src/main/resources/Monster.CSV");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            text = scanner.nextLine();
            Pattern pattern = Pattern.compile(monsterName + ",(\\d+),(\\S+?),(\\S+?),(\\S+?),(\\d+),(\\d+),(.+?)," +
                    "(\\d+)");
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                setLevel(Integer.parseInt(matcher.group(1)));
                setState(matcher.group(3));
                super.type = matcher.group(4);
                setDefAttPower(Integer.parseInt(matcher.group(5)));
                setDefDefPower(Integer.parseInt(matcher.group(6)));
                setDescription(matcher.group(7));
                setPrice(Integer.parseInt(matcher.group(8)));
            }
        }
    }


    public int getAttPower() {
        return this.attPower;
    }

    public void setAttPower(int attPower) {
        this.attPower = attPower;
    }

    public int getDefPower() {
        return this.defPower;
    }

    public void setDefPower(int defPower) {
        this.defPower = defPower;
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

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
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

    public int getTimesAffected() {
        return this.timesAffected;
    }

    public void increaseTimesAffected() {
        this.timesAffected++;
    }

}
