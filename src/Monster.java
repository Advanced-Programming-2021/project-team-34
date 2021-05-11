import java.util.ArrayList;

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
    public Monster(int price, String monsterName, String type, String description, int defAttPower, int defDefPower, int level) {
        super(price, monsterName, type, description);
        setDefAttPower(defAttPower);
        setDefDefPower(defDefPower);
        setLevel(level);
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
