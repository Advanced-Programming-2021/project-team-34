package Model;

import java.util.ArrayList;

public class Player {

    private static final int defaultLifePoint = 8000;

    private User user;
    private int lifePoint;
    private int score;
    private ArrayList<Card> inHandCards;
    private ArrayList<Card> inDeckCards;
    private ArrayList<Card> graveyard;
    private ArrayList<Card> monstersOnTheField;
    private ArrayList<Card> spellAndTrapsOnTheField;
    private Card fZ;

    public Player(User user) {
        this.user = user;
        this.lifePoint = defaultLifePoint;
        this.score = 0;
    }

    public void increaseLifePoint(int lifePointToIncrease) {
        this.lifePoint = this.lifePoint + lifePointToIncrease;
    }

    public void decreaseLifePoint(int lifePointToDecrease) {
        this.lifePoint = Math.max(0, this.lifePoint - lifePointToDecrease);
    }

    public void drawACardFromDeck() {

    }

    public void summon(Monster monsterToSummon) {

    }

    public void flipSummon(Monster monsterToFlipSummon) {

    }

//    public ArrayList<Card> getEffectiveCardsCanEffect() {
//
//    }
//
//    public void setASpellOrTrap(SpellAndTrap cardToSet) {
//
//    }
//
//    public void activateASpellOrTrap(SpellAndTrap cardToActivate) {
//
//    }

    public Card getFZ() {
        return fZ;
    }
}
