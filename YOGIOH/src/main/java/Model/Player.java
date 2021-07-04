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
    private ArrayList<CardInGame> inHandCards = new ArrayList<>(6);
    private ArrayList<CardInGame> inDeckCards = new ArrayList<>(60);
    private ArrayList<CardInGame> graveyard = new ArrayList<>();
    private ArrayList<MonsterInGame> monstersOnTheField = new ArrayList<>(5);
    private ArrayList<SpellAndTrapInGame> spellAndTrapsOnTheField = new ArrayList<>(5);
    private ArrayList<Integer> playerLPs = new ArrayList<>();
    private CardInGame fZ;

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

    public CardInGame getFZ() {
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
