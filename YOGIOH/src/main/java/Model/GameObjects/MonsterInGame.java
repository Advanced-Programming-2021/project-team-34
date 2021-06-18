package Model.GameObjects;

import Model.Monster;

public class MonsterInGame {
    Monster monster;
    int defencePower;
    int attackPower;
    MonsterInGameState state = MonsterInGameState.IN_OUR_DECK;
    private int timesAffected = 0;

    public MonsterInGame(Monster monster) {
        this.monster = monster;
        defencePower = monster.getDefDefPower();
        attackPower = monster.getDefAttPower();
    }

    public void increaseTimesAffected() {
        this.timesAffected++;
    }
}
