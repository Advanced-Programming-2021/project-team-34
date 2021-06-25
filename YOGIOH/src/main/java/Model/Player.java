package Model;

import Model.GameObjects.CardInGame;
import Model.GameObjects.MonsterInGame;
import Model.GameObjects.SpellAndTrapInGame;

import java.util.ArrayList;

public class Player {

    private static final int defaultLifePoint = 8000;

    private User user;
    private int lifePoint;
    private int score;
    private ArrayList<CardInGame> inHandCards;
    private ArrayList<CardInGame> inDeckCards;
    private ArrayList<CardInGame> graveyard;
    private ArrayList<MonsterInGame> monstersOnTheField;
    private ArrayList<SpellAndTrapInGame> spellAndTrapsOnTheField;
    private ArrayList<Integer> playerLPs = new ArrayList<>();
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

    public User getUser() {
        return user;
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public ArrayList<CardInGame> getInHandCards() {
        return inHandCards;
    }

    public ArrayList<CardInGame> getInDeckCards() {
        return inDeckCards;
    }

    public ArrayList<CardInGame> getGraveyard() {
        return graveyard;
    }

    public ArrayList<MonsterInGame> getMonstersOnTheField() {
        return monstersOnTheField;
    }

    public ArrayList<SpellAndTrapInGame> getSpellAndTrapsOnTheField() {
        return spellAndTrapsOnTheField;
    }

    public Card getfZ() {
        return fZ;
    }

    public int getMaxLP(){
        int maxLP = playerLPs.get(0);
        for (int i = 1; i < playerLPs.size(); i++){
            if (playerLPs.get(i) > maxLP){
                maxLP = playerLPs.get(i);
            }
        }
        return maxLP;
    }
}
