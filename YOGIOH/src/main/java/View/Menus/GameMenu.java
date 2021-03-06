package View.Menus;

import Controller.CommandHelper.Command;
import Controller.CommandHelper.CommandType;
import Controller.Connection;
import Controller.MenuController;
import Controller.MenuNames;
import Model.GameObjects.CardInGame;
import Model.GameObjects.CardInGameState;
import Model.GameObjects.MonsterInGame;
import Model.GameObjects.SpellAndTrapInGame;
import Model.Player;

public class GameMenu extends ViewMenu {
    static boolean toContinue = true;
    static Player currentPlayer, opponentPlayer; /* Be careful : set these two variables before calling run */
    public static void run() {
        Controller.Menus.GameMenu.initializeViewPlayers();
        initializeMenu();
        doMain();
    }

    private static void doMain() {
        String input;
        Command myCommand;
        String typeOfMyCommand;
        while (toContinue) {
            showGamePlay();
            manyLinesAfter(2);
            print("------------------\ninput a command : ");
            input = input();
            myCommand = new Command(input);
            typeOfMyCommand = myCommand.getType();
            switch (typeOfMyCommand) {
                case "invalid type":
                    print("The command you inputted is not valid in this menu!!!");
                    break;
                case "exit":
                    MenuController.setMenuName(MenuNames.MainMenu);
                    toContinue = false;
                    break;
                case "show current menu":
                    print("Game Menu");
                    break;
                case "select card":
                    selectCard(myCommand);
                    break;
                case "summon":
                    summon(myCommand);
                    break;
                case "next phase":
                    nextPhase();
                    break;
                case "set":
                    set();
                    break;
                case "set position":
                    setPosition(myCommand);
                    break;
                case "flip summon":
                    flipSummon();
                    break;
                case "attack":
                    attack(myCommand);
                    break;
                case "active effect":
                    activeEffect();
                    break;
                case "show graveyard":
                    showGraveYard();
                    break;
                case "surrender":
                    surrender();
                    break;
            }
            getConfirmation();
        }
    }

    private static void surrender() {
        String result = Connection.sendMessageToTheServer("game surrender token " +
                MenuController.getToken());
    }

    private static void showGraveYard() {
        manyLinesAfter();
        showTitle("SHOWING GRAVEYARDS");
        showTitle("OPPONENT GRAVEYARD");
        if (opponentPlayer.getGraveyard().size() == 0) {
            print("graveyard empty");
        } else {
            for (CardInGame card :
                    opponentPlayer.getGraveyard()) {
                print(card.getCard().getName() + ":" + card.getCard().getDescription());
            }
        }
        showTitle("YOUR GRAVEYARD");
        if (currentPlayer.getGraveyard().size() == 0) {
            print("graveyard empty");
        } else {
            for (CardInGame card :
                    currentPlayer.getGraveyard()) {
                print(card.getCard().getName() + ":" + card.getCard().getDescription());
            }
        }
        while (!"back".equals(input())) {
            print("input \"back\" to go back : ");
        }
    }

    private static void activeEffect() {
        String success = Connection.sendMessageToTheServer("game active effect token " + MenuController.getToken());
        if (success.equals("success")) {
            print("spell activated");
        } else {
            print(success);
        }
    }

    private static void attack(Command command) {
        String field = command.getField("attack");
        String result;
        if (field.equals("direct")) {
            result = Connection.sendMessageToTheServer("game direct attack token " + MenuController.getToken());
        } else {
            result = Connection.sendMessageToTheServer("game attack to a monster token " +
                    MenuController.getToken() + " monsterNumber " + command.getField("attack"));
        }
        print(result);
    }

    private static void flipSummon() {
        String success = Connection.sendMessageToTheServer("game flip summon token " + MenuController.getToken());
        if (success.equals("success")) {
            print("flip summoned successfully");
        } else {
            print(success);
        }
    }

    private static void setPosition(Command command) {
        String positionToSet = command.getField("position");
        String success = Connection.sendMessageToTheServer("game set position " + positionToSet + " token "
                + MenuController.getToken());
        if (success.equals("success")) {
            print("monster card position changed successfully");
        } else {
            print(success);
        }
    }

    private static void set() {
        String success = Connection.sendMessageToTheServer("game set token " + MenuController.getToken());
        if (success.equals("success")) {
            print("set successfully");
        } else {
            print(success);
        }
    }

    private static void nextPhase() {
        String result = Connection.sendMessageToTheServer("game next phase token " +
                MenuController.getToken());
        print(result);
    }

    private static void summon(Command command) {
        String success = Connection.sendMessageToTheServer("game summon token " +
                MenuController.getToken());
        if (success.equals("success")) {
            print("summoned successfully");
        } else {
            print(success);
        }
    }

    private static void selectCard(Command command) {
        String cardAddress = command.getField("select");
        String success;
        if (cardAddress.equals("-d")) {
            success = Connection.sendMessageToTheServer("game unselect card token " +
                    MenuController.getToken());
            if (success.equals("success")) {
                print("card deselected");
            }
        } else {
            success = Connection.sendMessageToTheServer("game select card " + cardAddress + " token " +
                    MenuController.getToken());
            if (success.equals("success")) {
                print("card selected");
            }
        }
        if (!success.equals("success")) {
            print(success);
        }

    }

    // show Game Play

    private static void showGamePlay() {
        showTitle("GAME MENU");
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

    // initialize
    private static void initializeMenu() {
        toContinue = true;
        Command.clearValidCommandTypes();
        initializeExitCommandType();
        initializeShowCurrentMenuCommandType();
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
        initializeNextPhaseCommandType();
        initializeShowCardCommandType();
    }

    private static void initializeShowCardCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("card");
        commandType.addField("show");
        commandType.setName("show card");
        Command.addCommandType(commandType);
    }

    private static void initializeNextPhaseCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("next phase");
        commandType.setMainPart("next phase");
        Command.addCommandType(commandType);
    }

    private static void initializeShowCurrentMenuCommandType() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("menu show-current");
        commandType.setName("show current menu");
        Command.addCommandType(commandType);
    }

    private static void initializeExitCommandType() {
        CommandType commandType = new CommandType();
        commandType.setName("exit");
        commandType.setMainPart("menu exit");
        Command.addCommandType(commandType);
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
        commandType.setMainPart("activate effect");
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

    public static int askWhichCardToSacrifice() {
        return 0;
    }
}
