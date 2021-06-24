package Model.GameObjects;

import Model.Monster;

public class MonsterInGame extends CardInGame {
    Monster monster;
    int defencePower;
    int attackPower;
    private int timesAffected = 0;

    public MonsterInGame(Monster monster) {
        this.monster = monster;
        super.card = monster;
        defencePower = monster.getDefDefPower();
        attackPower = monster.getDefAttPower();
    }

    public int getTimesAffected() {
        return timesAffected;
    }
    public void increaseTimesAffected() {
        this.timesAffected++;
    }

    public Monster getMonster() {
        return monster;
    }

    public int getDefencePower() {
        return defencePower;
    }

    public int getAttackPower() {
        return attackPower;
    }

}
