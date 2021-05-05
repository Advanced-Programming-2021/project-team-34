import java.util.ArrayList;
public class Monster extends Card {
    private int attPower;//attack power
    private int defPower;//defence power
    private int level;
    private String state;
    private int defAttPower;//default attack power
    private int defDefPower;//default defence power
    public String Ritual;
    private ArrayList<Monster> monsters;
    private String monsterName;
    private int timesAffected=0;
    //TO DO : 
    /* To Add Constructor */
    //!!!!!!!!!!!!!!!!!!!!!!
    //TO DO : 
    /* To Add All Methods */

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
        return this.defAttPower;
    }

    public void setDefAttPower(int defAttPower) {
        this.defAttPower = defAttPower;
    }

    public int getDefDefPower() {
        return this.defDefPower;
    }

    public void setDefDefPower(int defDefPower) {
        this.defDefPower = defDefPower;
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
