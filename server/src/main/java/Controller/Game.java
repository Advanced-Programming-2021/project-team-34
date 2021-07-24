package Controller;

import Model.GameObjects.CardInGame;
import Model.GameObjects.CardInGameState;
import Model.GameObjects.MonsterInGame;
import Model.GameObjects.SpellAndTrapInGame;
import Model.Player;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Controller.GamePhase.DrawPhase;

public class Game { // todo : complete the body and check if needed extends Thread
    Player player1 , player2;
    private Player currentPlayer;
    private Player otherPlayer;
    private CardInGame selectedCard;
    private GamePhase phase = DrawPhase;
    private String error;
    private boolean hasSetOrSummonMonster = false;
    private ArrayList<MonsterInGame> attackedMonsters = new ArrayList<>();
    private ArrayList<MonsterInGame> changedPositions = new ArrayList<>();
    private ArrayList<MonsterInGame> setMonsters = new ArrayList<>();

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        Player currentPlayer = player1;
        Player otherPlayer = player2;
        CardInGame selectedCard;
        GamePhase phase = DrawPhase;
        boolean hasSetOrSummonMonster = false;
        ArrayList<MonsterInGame> attackedMonsters = new ArrayList<>();
        ArrayList<MonsterInGame> changedPositions = new ArrayList<>();
        ArrayList<MonsterInGame> setMonsters = new ArrayList<>();
        player1.setGame(this);
        player2.setGame(this);
    }


    public boolean unselect() {
        if (selectedCard == null) {
            error = "no card is selected yet";
            return false;
        }
        return true;
    }

    public boolean select(String cardAddress) {
        Pattern pattern = Pattern.compile("^(monster|spell|monster opponent|spell opponent|hand) ([123456])$");
        Matcher matcher = pattern.matcher(cardAddress);
        if (matcher.find()) {
            int number = Integer.parseInt(matcher.group(2));
            switch (matcher.group(1)) {
                case "monster":
                    return selectingMonster(number);
                case "spell":
                    return selectingSpell(number);
                case "monster opponent":
                    return selectingOpponentMonster(number);
                case "spell opponent":
                    return selectingOpponentSpell(number);
                case "hand":
                    return selectingCardOfHand(number);
            }
        } else {
            switch (cardAddress) {
                case "field":
                    return selectingFieldZone(currentPlayer);
                case "field opponent":
                    return selectingFieldZone(otherPlayer);
                default:
                    error = "invalid selection";
                    return false;
            }
        }
        return false;
    }

    private boolean selectingMonster(int number) {
        return monsterNumberError(number, currentPlayer);
    }

    private boolean selectingOpponentMonster(int number) {
        return monsterNumberError(number, otherPlayer);
    }

    private boolean monsterNumberError(int number, Player currentPlayer) {
        if (number == 6) {
            error = "invalid selection";
            return false;
        } else if (currentPlayer.getMonstersOnTheField().get(number - 1) == null) {
            error = "no card found in the given position";
            return false;
        } else {
            selectedCard = currentPlayer.getMonstersOnTheField().get(number - 1);
            return true;
        }
    }

    private boolean selectingSpell(int number) {
        return spellNumberError(number, currentPlayer);
    }

    private boolean selectingOpponentSpell(int number) {
        return spellNumberError(number, otherPlayer);
    }

    private boolean spellNumberError(int number, Player currentPlayer) {
        if (number == 6) {
            error = "invalid selection";
            return false;
        } else if (currentPlayer.getSpellAndTrapsOnTheField().get(number - 1) == null) {
            error = "no card found in the given position";
            return false;
        } else {
            selectedCard = currentPlayer.getSpellAndTrapsOnTheField().get(number - 1);
            return true;
        }
    }

    private boolean selectingCardOfHand(int number) {
        if (currentPlayer.getInHandCards().get(number - 1) == null) {
            error = "no card found in the given position";
            return false;
        } else {
            selectedCard = currentPlayer.getInHandCards().get(number - 1);
            return true;
        }
    }

    private boolean selectingFieldZone(Player player) {
        if (player.getFZ() == null) {
            error = "no card found in the given position";
            return false;
        } else {
            selectedCard = player.getFZ();
            return true;
        }
    }

    public String getError() {
        return error;
    }

    public boolean summon() {
        if (selectedCard == null) {
            error = "no card is selected yet";
            return false;
        }
        boolean isInHand = false;
        for (CardInGame card : currentPlayer.getInHandCards()) {
            if (card == selectedCard) {
                isInHand = true;
                break;
            }
        }
        if (!isInHand || !selectedCard.getType().equals("monster")) {
            error = "you can’t summon this card";
            return false;
        } else if (phase != GamePhase.MainPhase1 && phase != GamePhase.MainPhase2) {
            error = "you can’t do this action in this phase";
            return false;
        } else if (currentPlayer.getMonstersOnTheField().size() > 4) {
            error = "monster card zone is full";
            return false;
        } else if (hasSetOrSummonMonster) {
            error = "you already summoned/set on this turn";
            return false;
        } else if (((MonsterInGame) selectedCard).getMonster().getLevel() <= 4) {
            currentPlayer.getMonstersOnTheField().add((MonsterInGame) selectedCard);
            selectedCard.setState(CardInGameState.IN_FIELD);
            hasSetOrSummonMonster = true;
            selectedCard = null;
            return true;
        } else if (((MonsterInGame) selectedCard).getMonster().getLevel() <= 6) {
            boolean isAnyMonster = false;
            for (MonsterInGame monster : currentPlayer.getMonstersOnTheField()) {
                if (monster != null) {
                    isAnyMonster = true;
                    break;
                }
            }
            if (!isAnyMonster) {
                error = "there are not enough cards for tribute";
                return false;
            } else {
                int monsterNumber = 0;// View.Menus.GameMenu.askWhichCardToSacrifice(); TODO : modify this
                if (monsterNumber == 0) {
                    error = "The performance canceled by user";
                    return false;
                } else if (currentPlayer.getMonstersOnTheField().get(monsterNumber - 1) == null) {
                    error = "there no monsters one this address";
                    return false;
                } else {
                    currentPlayer.getGraveyard().add(currentPlayer.getMonstersOnTheField().get(monsterNumber - 1));
                    currentPlayer.getMonstersOnTheField().remove(monsterNumber - 1);
                    currentPlayer.getMonstersOnTheField().add((MonsterInGame) selectedCard);
                    selectedCard.setState(CardInGameState.IN_FIELD);
                    hasSetOrSummonMonster = true;
                    selectedCard = null;
                    return true;
                }
            }
        } else {
            int counter = 0;
            boolean areEnoughMonsters = false;
            for (MonsterInGame monster : currentPlayer.getMonstersOnTheField()) {
                if (monster != null) {
                    counter++;
                    if (counter == 2) {
                        areEnoughMonsters = true;
                        break;
                    }
                }
            }
            if (!areEnoughMonsters) {
                error = "there are not enough cards for tribute";
                return false;
            } else {
                int number1 = 0;//View.Menus.GameMenu.askWhichCardToSacrifice(); TODO : modify this
                if (number1 == 0) {
                    error = "The performance canceled by user";
                    return false;
                }
                int number2 = 0;//View.Menus.GameMenu.askWhichCardToSacrifice();TODO : modify this
                if (number2 == 0) {
                    error = "The performance canceled by user";
                    return false;
                } else if (number1 == number2) {
                    error = "The performance canceled by user";
                    return false;
                } else if (currentPlayer.getMonstersOnTheField().get(number1 - 1) == null ||
                        currentPlayer.getMonstersOnTheField().get(number2 - 1) == null) {
                    error = "there no monsters one this address";
                    return false;
                } else {
                    currentPlayer.getGraveyard().add(currentPlayer.getMonstersOnTheField().get(number1 - 1));
                    currentPlayer.getGraveyard().add(currentPlayer.getMonstersOnTheField().get(number2 - 1));
                    currentPlayer.getMonstersOnTheField().remove(number1 - 1);
                    currentPlayer.getMonstersOnTheField().remove(number2 - 1);
                    currentPlayer.getMonstersOnTheField().add((MonsterInGame) selectedCard);
                    selectedCard.setState(CardInGameState.IN_FIELD);
                    hasSetOrSummonMonster = true;
                    selectedCard = null;
                    return true;
                }
            }
        }
    }

    public String nextPhase() {
        switch (phase) {
            case DrawPhase:
                phase = GamePhase.StandbyPhase;
                return "phase: Standby phase";
            case StandbyPhase:
                phase = GamePhase.MainPhase1;
                return "phase: MainPhase1";
            case MainPhase1:
                phase = GamePhase.BattlePhase;
                return "phase: BattlePhase";
            case BattlePhase:
                phase = GamePhase.MainPhase2;
                return "phase: MainPhase2";
            case MainPhase2:
                phase = GamePhase.EndPhase;
                return "phase: EndPhase";
            case EndPhase:
                phase = DrawPhase;
                //View.Menus.GameMenu.changeTurn(); //TODO
                changeTurn();
                return "phase: DrawPhase";
        }
        return null;
    }

    private void changeTurn() {
        Player player = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = player;
        hasSetOrSummonMonster = false;
        attackedMonsters.clear();
        changedPositions.clear();
        setMonsters.clear();
    }

    public boolean set() {
        if (selectedCard == null) {
            error = "no card is selected yet";
            return false;
        } else {
            boolean isInHand = false;
            for (CardInGame card : currentPlayer.getInHandCards()) {
                if (card == selectedCard) {
                    isInHand = true;
                    break;
                }
            }
            if (!isInHand) {
                error = "you can’t set this card";
                return false;
            } else if (phase != GamePhase.MainPhase1 && phase != GamePhase.MainPhase2) {
                error = "you can’t do this action in this phase";
                return false;
            } else if (selectedCard.getType().equals("monster")) {
                if (currentPlayer.getMonstersOnTheField().size() > 4) {
                    error = "monster card zone is full";
                    return false;
                } else if (hasSetOrSummonMonster) {
                    error = "you already summoned/set on this turn";
                    return false;
                } else {
                    currentPlayer.getMonstersOnTheField().add((MonsterInGame) selectedCard);
                    selectedCard.setState(CardInGameState.IN_FIELD_FLIPPED);
                    setMonsters.add((MonsterInGame) selectedCard);
                    hasSetOrSummonMonster = true;
                    selectedCard = null;
                    return true;
                }
            } else if (selectedCard.getType().equals("spell or trap")) {
                if (currentPlayer.getSpellAndTrapsOnTheField().size() > 4) {
                    error = "spell card zone is full";
                    return false;
                } else {
                    currentPlayer.getSpellAndTrapsOnTheField().add((SpellAndTrapInGame) selectedCard);
                    selectedCard.setState(CardInGameState.IN_FIELD_FLIPPED);
                    selectedCard = null;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean setPosition(String positionToSet) {
        if (selectedCard == null) {
            error = "no card is selected yet";
            return false;
        } else {
            boolean isOnMonsterZone = false;
            for (CardInGame card : currentPlayer.getMonstersOnTheField()) {
                if (card == selectedCard) {
                    isOnMonsterZone = true;
                    break;
                }
            }
            if (!isOnMonsterZone) {
                error = "you can’t change this card position";
                return false;
            } else if (phase != GamePhase.MainPhase1 && phase != GamePhase.MainPhase2) {
                error = "you can’t do this action in this phase";
                return false;
            } else {
                if (positionToSet.equals("attack")) {
                    if (selectedCard.getState() != CardInGameState.IN_DEFENCE_POSITION) {
                        error = "this card is already in the wanted position";
                        return false;
                    }
                    boolean hasChangedPosition = false;
                    for (MonsterInGame monster : changedPositions) {
                        if (monster == selectedCard) {
                            hasChangedPosition = true;
                            break;
                        }
                    }
                    if (hasChangedPosition) {
                        error = "you already changed this card position in this turn";
                        return false;
                    } else {
                        changedPositions.add((MonsterInGame) selectedCard);
                        selectedCard.setState(CardInGameState.IN_FIELD);
                        selectedCard = null;
                        return true;
                    }
                } else {
                    if (selectedCard.getState() != CardInGameState.IN_FIELD) {
                        error = "this card is already in the wanted position";
                        return false;
                    }
                    boolean hasChangedPosition = false;
                    for (MonsterInGame monster : changedPositions) {
                        if (monster == selectedCard) {
                            hasChangedPosition = true;
                            break;
                        }
                    }
                    if (hasChangedPosition) {
                        error = "you already changed this card position in this turn";
                        return false;
                    } else {
                        changedPositions.add((MonsterInGame) selectedCard);
                        selectedCard.setState(CardInGameState.IN_DEFENCE_POSITION);
                        selectedCard = null;
                        return true;
                    }
                }
            }
        }
    }

    public boolean flipSummon() {
        if (selectedCard == null) {
            error = "no card is selected yet";
            return false;
        } else {
            boolean isOnMonsterZone = false;
            for (CardInGame card : currentPlayer.getMonstersOnTheField()) {
                if (card == selectedCard) {
                    isOnMonsterZone = true;
                    break;
                }
            }
            if (!isOnMonsterZone) {
                error = "you can’t change this card position";
                return false;
            } else if (phase != GamePhase.MainPhase1 && phase != GamePhase.MainPhase2) {
                error = "you can’t do this action in this phase";
                return false;
            } else {
                boolean isSelectedCardSetThisTurn = false;
                for (MonsterInGame monster : setMonsters) {
                    if (monster == selectedCard) {
                        isSelectedCardSetThisTurn = true;
                        break;
                    }
                }
                if (isSelectedCardSetThisTurn || selectedCard.getState() != CardInGameState.IN_DEFENCE_POSITION) {
                    error = "you can’t flip summon this card";
                    return false;
                } else {
                    changedPositions.add((MonsterInGame) selectedCard);
                    selectedCard.setState(CardInGameState.IN_FIELD);
                    selectedCard = null;
                    return true;
                }
            }
        }
    }

    public String directAttack() {
        if (selectedCard == null) return "no card is selected yet";
        boolean isOnMonsterZone = false;
        for (CardInGame card : currentPlayer.getMonstersOnTheField()) {
            if (card == selectedCard) {
                isOnMonsterZone = true;
                break;
            }
        }
        if (!isOnMonsterZone) return "you can’t attack with this card";
        else if (phase != GamePhase.BattlePhase) return "you can’t do this action in this phase";
        boolean hasAttacked = false;
        for (MonsterInGame monster : attackedMonsters) {
            if (monster == selectedCard) {
                hasAttacked = true;
                break;
            }
        }
        if (hasAttacked) return "this card already attacked";
        if (otherPlayer.getMonstersOnTheField().size() == 0) return "you can’t attack the opponent directly";
        int lifePointToDecrease = ((MonsterInGame) selectedCard).getAttackPower();
        otherPlayer.decreaseLifePoint(lifePointToDecrease);
        attackedMonsters.add((MonsterInGame) selectedCard);
        selectedCard = null;
        return "you opponent receives " + lifePointToDecrease + " battle damage";
    }

    public String attackToAMonster(String address) { // to check if address is valid or not
        if (selectedCard == null) return "no card is selected yet";
        boolean isOnMonsterZone = false;
        for (CardInGame card : currentPlayer.getMonstersOnTheField()) {
            if (card == selectedCard) {
                isOnMonsterZone = true;
                break;
            }
        }
        if (!isOnMonsterZone) return "you can’t attack with this card";
        else if (phase != GamePhase.BattlePhase) return "you can’t do this action in this phase";
        boolean hasAttacked = false;
        for (MonsterInGame monster : attackedMonsters) {
            if (monster == selectedCard) {
                hasAttacked = true;
                break;
            }
        }
        if (hasAttacked) return "this card already attacked";
        else {
            try {
                int number = Integer.parseInt(address);
                if (number < 1 || number > 5) return "invalid command";
                else if (otherPlayer.getMonstersOnTheField().size() < number ||
                        otherPlayer.getMonstersOnTheField().get(number - 1) == null) {
                    return "there is no card to attack here";
                } else {
                    hasSetOrSummonMonster = true;
                    attackedMonsters.add((MonsterInGame) selectedCard);
                    if (otherPlayer.getMonstersOnTheField().get(number - 1).getState() == CardInGameState.IN_FIELD) {
                        int damage = ((MonsterInGame) selectedCard).getAttackPower() -
                                otherPlayer.getMonstersOnTheField().get(number - 1).getAttackPower();
                        if (damage > 0) {
                            otherPlayer.decreaseLifePoint(damage);
                            otherPlayer.getGraveyard().add(otherPlayer.getMonstersOnTheField().get(number - 1));
                            otherPlayer.getMonstersOnTheField().set(number - 1, null);
                            selectedCard = null;
                            return "your opponent’s monster is destroyed and your opponent receives " + damage +
                                    " battle damage";
                        } else if (damage == 0) {
                            otherPlayer.getGraveyard().add(otherPlayer.getMonstersOnTheField().get(number - 1));
                            otherPlayer.getMonstersOnTheField().set(number - 1, null);
                            currentPlayer.getGraveyard().add(selectedCard);
                            int index = -1;
                            for (int i = 0; i < currentPlayer.getMonstersOnTheField().size(); i++) {
                                if (currentPlayer.getMonstersOnTheField().get(i) == selectedCard) {
                                    index = i;
                                }
                            }
                            currentPlayer.getMonstersOnTheField().set(index, null);
                            selectedCard = null;
                            return "both you and your opponent monster cards are destroyed and no one receives damage";
                        } else {
                            currentPlayer.decreaseLifePoint(-damage);
                            int index = -1;
                            for (int i = 0; i < currentPlayer.getMonstersOnTheField().size(); i++) {
                                if (currentPlayer.getMonstersOnTheField().get(i) == selectedCard) {
                                    index = i;
                                }
                            }
                            currentPlayer.getGraveyard().add(currentPlayer.getMonstersOnTheField().get(index));
                            currentPlayer.getMonstersOnTheField().set(index, null);
                            selectedCard = null;
                            return "Your monster card is destroyed and you received " + (-damage) + " battle damage";
                        }
                    } else if (otherPlayer.getMonstersOnTheField().get(number - 1).getState() ==
                            CardInGameState.IN_DEFENCE_POSITION) {
                        int damage = ((MonsterInGame) selectedCard).getAttackPower() -
                                otherPlayer.getMonstersOnTheField().get(number - 1).getDefencePower();
                        if (damage > 0) {
                            otherPlayer.getGraveyard().add(otherPlayer.getMonstersOnTheField().get(number - 1));
                            otherPlayer.getMonstersOnTheField().set(number - 1, null);
                            selectedCard = null;
                            return "the defense position monster is destroyed";
                        } else if (damage == 0) {
                            selectedCard = null;
                            return "no card is destroyed";
                        } else {
                            currentPlayer.decreaseLifePoint(-damage);
                            selectedCard = null;
                            return "no card is destroyed and you received " + (-damage) + " battle damage";
                        }
                    } else {
                        String cardName = otherPlayer.getMonstersOnTheField().get(number - 1).getMonster().getName();
                        int damage = ((MonsterInGame) selectedCard).getAttackPower() -
                                otherPlayer.getMonstersOnTheField().get(number - 1).getDefencePower();
                        if (damage > 0) {
                            otherPlayer.getGraveyard().add(otherPlayer.getMonstersOnTheField().get(number - 1));
                            otherPlayer.getMonstersOnTheField().set(number - 1, null);
                            selectedCard = null;
                            return "opponent’s monster card was " + cardName +
                                    " and the defense position monster is destroyed";
                        } else if (damage == 0) {
                            selectedCard = null;
                            return "opponent’s monster card was " + cardName + " and no card is destroyed";
                        } else {
                            currentPlayer.decreaseLifePoint(-damage);
                            selectedCard = null;
                            return "opponent’s monster card was " + cardName +
                                    " and no card is destroyed and you received " + (-damage) + " battle damage";
                        }
                    }
                }
            } catch (Exception exception) {
                return "invalid command";
            }
        }
    }

    public boolean activeEffect() {
        return false;
    }

    public void surrender() {
    }

    public void initializeViewPlayers() { //TODO : modify it
//        View.Menus.GameMenu.setCurrentPlayer(otherPlayer);
//        View.Menus.GameMenu.setOpponentPlayer(currentPlayer);
    }

}
