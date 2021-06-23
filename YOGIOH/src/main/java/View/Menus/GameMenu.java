package View.Menus;

import Model.GameObjects.CardInGame;
import Model.GameObjects.CardInGameState;
import Model.GameObjects.MonsterInGame;
import Model.GameObjects.SpellAndTrapInGame;
import Model.Player;
import View.CommandHelper.Command;
import View.CommandHelper.CommandType;

public class GameMenu extends ViewMenu {
    static boolean toContinue = true;
    static Player currentPlayer, opponentPlayer; /* Be careful : set these two variables before calling run */
    public static void run() {
        initializeMenu();
        doMain();
    }

    private static void doMain() {
        String input;
        Command myCommand;
        String typeOfMyCommand;
        showGamePlay();
    }

    private static void showGamePlay() {
        showOpponentsBoard();
        print("\n--------------------------------------------\n");
        showCurrentsBoard();
    }

    private static void showCurrentsBoard() {
        showCardInFZ(currentPlayer);
        write("\t\t\t\t\t\t");
        showCardsInGraveYard(currentPlayer);
        showCardsInMonsterZone(currentPlayer);
        showCardsInSpellAndTrapZone(currentPlayer);
        write("\t\t\t\t\t\t");
        showCardsInDeck(currentPlayer);
        showNicknameAndLifePoint(currentPlayer);
    }

    private static void showOpponentsBoard() {
        showNicknameAndLifePoint(opponentPlayer);
        write("\t");showCardsInHand(opponentPlayer);
        showCardsInDeck(opponentPlayer);
        showCardsInSpellAndTrapZone(opponentPlayer);
        showCardsInMonsterZone(opponentPlayer);
        showCardsInGraveYard(opponentPlayer);
        write("\t\t\t\t\t\t");
        showCardInFZ(opponentPlayer);

    }

    private static void showCardInFZ(Player player) {
        if (player.getFZ() == null) {
            write("E");
        } else write("O");
    }

    private static void showCardsInGraveYard(Player player) {
        write(player.getGraveyard().size()+"");
    }

    private static void showCardsInSpellAndTrapZone(Player player) {
        // !!!!!!!!!!!!!!!!!!!!!!!111 To edit!
        print(  "\t"+getTextOfSePellAndTrapToShow(player.getSpellAndTrapsOnTheField().get((player == currentPlayer) ? (4) : (3)))+
                "\t"+getTextOfSePellAndTrapToShow(player.getSpellAndTrapsOnTheField().get((player == currentPlayer) ? (2) : (1)))+
                "\t"+getTextOfSePellAndTrapToShow(player.getSpellAndTrapsOnTheField().get(0))+
                "\t"+getTextOfSePellAndTrapToShow(player.getSpellAndTrapsOnTheField().get((player == currentPlayer) ? (1) : (2)))+
                "\t"+getTextOfSePellAndTrapToShow(player.getSpellAndTrapsOnTheField().get((player == currentPlayer) ? (3) : (4))));
    }

    private static String getTextOfSePellAndTrapToShow(SpellAndTrapInGame spellAndTrapInGame) {
        if (spellAndTrapInGame == null) {
            return "E";
        }
        if (spellAndTrapInGame.getState().equals(CardInGameState.IN_FIELD)) {
            return "O";
        }
        if (spellAndTrapInGame.getState().equals(CardInGameState.IN_FIELD_FLIPPED)) {
            return "H";
        }
        return "??";
    }

    private static String getTextOfMonsterCardToShow(MonsterInGame monsterInGame) {
        if (monsterInGame == null) {
            return "E";
        }
        if (monsterInGame.getState().equals(CardInGameState.IN_FIELD_FLIPPED)) {
            return "DH";
        }
        if (monsterInGame.getState().equals(CardInGameState.IN_FIELD)) {
            return "OO";
        }
        if (monsterInGame.getState().equals(CardInGameState.IN_DEFENCE_POSITION)) {
            return "DO";
        }
        return "??";
    }

    private static void showCardsInMonsterZone(Player player) {
        print(  "\t"+getTextOfMonsterCardToShow(player.getMonstersOnTheField().get((player == currentPlayer) ? (4) : (3)))+
                "\t"+getTextOfMonsterCardToShow(player.getMonstersOnTheField().get((player == currentPlayer) ? (2) : (1)))+
                "\t"+getTextOfMonsterCardToShow(player.getMonstersOnTheField().get(0))+
                "\t"+getTextOfMonsterCardToShow(player.getMonstersOnTheField().get((player == currentPlayer) ? (1) : (2)))+
                "\t"+getTextOfMonsterCardToShow(player.getMonstersOnTheField().get((player == currentPlayer) ? (3) : (4))));
    }

    private static void showCardsInDeck(Player player) {
        int numberOfCardsInDeck = player.getInDeckCards().size();
        print(numberOfCardsInDeck+"");
    }

    private static void showCardsInHand(Player player) {
        for (CardInGame cardInHand :
                player.getInHandCards()) {
            print("c\t");
        }
    }

    private static void showNicknameAndLifePoint(Player player) {
        print(player.getUser().getNickname()+":"+player.getLifePoint());
    }

    private static void initializeMenu() {
        toContinue = true;
        Command.clearValidCommandTypes();
        initializeSelectCardCommandType(); // to forget the selected card , the "select" field of it should be "d"
        initializeSummonCommandType();
        initializeSetCommandType();
        initializeSetPositionCommandType();
        initializeFlipSummonCommandType();
        initializeAttackCommandType();
        initializeActiveEffectCommandType();
        initializeShowGraveyardCommandType();
        initializeBackCommandType();
        initializeShowCommandType();
        initializeCancelCommandType();
        initializeSurrenderCommandType();
    }

    private static void initializeSurrenderCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("surrender");
        commandType.setName("surrender");
        Command.addCommandType(commandType);
    }

    private static void initializeCancelCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("cancel");
        commandType.setMainPart("cancel");
        Command.addCommandType(commandType);
    }

    private static void initializeShowCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("card show selected");
        Command.addCommandType(commandType);
    }

    private static void initializeBackCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("back");
        commandType.setName("back");
        Command.addCommandType(commandType);
    }

    private static void initializeShowGraveyardCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("show graveyard");
        commandType.setMainPart("show graveyard");
        Command.addCommandType(commandType);
    }

    private static void initializeActiveEffectCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("active effect");
        commandType.setName("active effect");
        Command.addCommandType(commandType);
    }

    private static void initializeAttackCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("");
        commandType.setName("attack");
        commandType.addField("attack");
        Command.addCommandType(commandType);
    }

    private static void initializeFlipSummonCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("flip summon");
        commandType.setMainPart("flip-summon");
        Command.addCommandType(commandType);
    }

    private static void initializeSetPositionCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("set position");
        commandType.setMainPart("set");
        commandType.addField("position");
        Command.addCommandType(commandType);
    }

    private static void initializeSetCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("set");
        commandType.setMainPart("set");
        Command.addCommandType(commandType);
    }

    private static void initializeSummonCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("summon");
        commandType.setName("summon");
        Command.addCommandType(commandType);
    }


    private static void initializeSelectCardCommandType() {
        CommandType commandType = new CommandType();
        //select <card address>
        commandType.setMainPart("");
        commandType.setName("select card");
        commandType.addField("select");
        Command.addCommandType(commandType);
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        GameMenu.currentPlayer = currentPlayer;
    }

    public static void setOpponentPlayer(Player opponentPlayer) {
        GameMenu.opponentPlayer = opponentPlayer;
    }

    public static void changeTurn() {
        Player player = currentPlayer;
        currentPlayer = opponentPlayer;
        opponentPlayer = currentPlayer;
    }
}
