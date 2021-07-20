package Controller;

import Controller.CommandHelper.Command;
import Controller.CommandHelper.CommandType;
import FinalStrings.CommandTypeMainParts;
import FinalStrings.CommandTypeNames;
import FinalStrings.CommandTypesFieldNames;

public class CommandTypeInitializer {
    /**
     * This method initializes CommandTypes
     */
    public static void run() {
        Command.clearValidCommandTypes();
        login();// login --username <username> --password <password>
        signUp();// signup --username <username> --password <password> --nickname <nickname>
        logout(); // logout --token <token>
        getUserInfo(); // get user info --token <token>
        getAllUsers(); // get all users
        changeNickname();// change nickname --token <token> --newNickname <newNickname>
        changePassword();// change password --token <token> --currentPassword <currentPassword> --newPassword <newPassword>
        changeAvatar();// change avatar --token <token> --newAvatar <newAvatar> // newAvatar is a number!
        sendMessage();// send message --token <token> --message <message> // DON'T FORGET '"' BEFORE AND AFTER THE MESSAGE!
        replyMessage();// send message --token <token> --message <message> --replyOn <repliedMessageID>
        increaseCoin();// increase coin --token <token> --value <value>
        getMessages(); // get messages
        requestGame(); // request game --round <1/3> --token <token>
        deleteRequestGame(); // request game --delete --token <token>
        showScoreboard(); // show scoreboard --token <token>
        buyCard(); // buy card --cardName <cardName> --token <token>
        sellCard(); // sell card --cardName <cardName> --token <token>
        addCard(); // add card --cardName <cardName> --token <token>
        removeCard(); // remove card --cardName <cardName> --token <token>
        forbidCard(); // forbid card --cardName <cardName> --token <token>
        unForbidCard(); // un forbid card --cardName <cardName> --token <token>
        getUserAvatar(); // get user avatar --username <username>
        gameSummon(); // game summon --token <token>
        gameNextPhase(); // game next phase --token <token>
        gameSurrender(); // game surrender --token <token>
        gameActiveEffect(); // game active effect --token <token>
        gameDirectAttack(); // game direct attack --token <token>
        gameAttackToAMonster(); // game attack to a monster --token <token> --monsterNumber <1/2/3/4/5>
        gameFlipSummon(); // game flip summon --token <token>
        gameSetPosition(); // game set --position <position> --token <token>
        gameSet(); // game set --token <token>
        gameSelectCard(); // game select --card <cardAddress> --token <token>
        gameUnselectCard(); // game unselect card --token <token>
    }

    private static void getAllUsers() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.GET_ALL_USERS);
        commandType.setMainPart(CommandTypeMainParts.GET_ALL_USERS);
        Command.addCommandType(commandType);
    }

    private static void getUserInfo() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.GET_USER_INFO);
        commandType.setMainPart(CommandTypeMainParts.GET_USER_INFO);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void gameUnselectCard() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.GAME_UNSELECT_CART);
        commandType.setMainPart(CommandTypeMainParts.GAME_UNSELECT_CARD);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void gameSelectCard() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.GAME_SELECT_CARD);
        commandType.setMainPart(CommandTypeMainParts.GAME_SELECT);
        commandType.addField(CommandTypesFieldNames.CARD_ADDRESS);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void gameSet() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.GAME_SET_CARD);
        commandType.setMainPart(CommandTypeMainParts.GAME_SET);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void gameSetPosition() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.GAME_SET_POSITION);
        commandType.setMainPart(CommandTypeMainParts.GAME_SET);
        commandType.addField(CommandTypesFieldNames.POSITION);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void gameFlipSummon() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.GAME_FLIP_SUMMON);
        commandType.setMainPart(CommandTypeMainParts.GAME_FLIP_SUMMON);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void gameAttackToAMonster() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.GAME_ATTACK_TO_A_MONSTER);
        commandType.setMainPart(CommandTypeMainParts.GAME_ATTACK_TO_A_MONSTER);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        commandType.addField(CommandTypesFieldNames.MONSTER_NUMBER);
        Command.addCommandType(commandType);
    }

    private static void gameDirectAttack() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.GAME_DIRECT_ATTACK);
        commandType.setMainPart(CommandTypeMainParts.GAME_DIRECT_ATTACK);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void gameActiveEffect() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.GAME_ACTIVE_EFFECT);
        commandType.setMainPart(CommandTypeMainParts.GAME_ACTIVE_EFFECT);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void gameSurrender() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.GAME_SURRENDER);
        commandType.setMainPart(CommandTypeMainParts.GAME_SURRENDER);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void gameNextPhase() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.GAME_NEXT_PHASE);
        commandType.setMainPart(CommandTypeMainParts.GAME_NEXT_PHASE);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void gameSummon() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.GAME_SUMMON);
        commandType.setMainPart(CommandTypeMainParts.GAME_SUMMON);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void getUserAvatar() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.GET_USER_AVATAR);
        commandType.setMainPart(CommandTypeMainParts.GET_USER_AVATAR);
        commandType.addField(CommandTypesFieldNames.USERNAME);
        Command.addCommandType(commandType);
    }

    private static void unForbidCard() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.UN_FORBID_CARD);
        commandType.setMainPart(CommandTypeMainParts.UN_FORBID_CARD);
        commandType.addField(CommandTypesFieldNames.CARD_NAME);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void forbidCard() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.FORBID_CARD);
        commandType.setMainPart(CommandTypeMainParts.FORBID_CARD);
        commandType.addField(CommandTypesFieldNames.CARD_NAME);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void removeCard() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.REMOVE_CARD);
        commandType.setMainPart(CommandTypeMainParts.REMOVE_CARD);
        commandType.addField(CommandTypesFieldNames.CARD_NAME);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void addCard() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.ADD_CARD);
        commandType.setMainPart(CommandTypeMainParts.ADD_CARD);
        commandType.addField(CommandTypesFieldNames.CARD_NAME);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void sellCard() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.SELL_CARD);
        commandType.setMainPart(CommandTypeMainParts.SELL_CARD);
        commandType.addField(CommandTypesFieldNames.CARD_NAME);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void buyCard() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.BUY_CARD);
        commandType.setMainPart(CommandTypeMainParts.BUY_CARD);
        commandType.addField(CommandTypesFieldNames.CARD_NAME);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void showScoreboard() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.SHOW_SCOREBOARD);
        commandType.setMainPart(CommandTypeMainParts.SHOW_SCOREBOARD);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void deleteRequestGame() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.DELETE_GAME_REQUEST);
        commandType.setMainPart(CommandTypeMainParts.REQUEST_GAME);
        commandType.addField(CommandTypesFieldNames.DELETE , false);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void requestGame() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.NEW_GAME_REQUEST);
        commandType.setMainPart(CommandTypeMainParts.REQUEST_GAME);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        commandType.addField(CommandTypesFieldNames.ROUND);
        Command.addCommandType(commandType);
    }

    private static void getMessages() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.GET_MESSAGES);
        commandType.setMainPart(CommandTypeMainParts.GET_MESSAGES);
        Command.addCommandType(commandType);
    }

    private static void replyMessage() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.REPLY_MESSAGE);
        commandType.setMainPart(CommandTypeMainParts.SEND_MESSAGE);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        commandType.addField(CommandTypesFieldNames.MESSAGE);
        commandType.addField(CommandTypesFieldNames.REPLY_ON);
        Command.addCommandType(commandType);
    }

    private static void increaseCoin() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.INCREASE_COIN);
        commandType.setMainPart(CommandTypeMainParts.INCREASE_COIN);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        commandType.addField(CommandTypesFieldNames.VALUE);
        Command.addCommandType(commandType);
    }

    private static void sendMessage() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.SEND_MESSAGE);
        commandType.setMainPart(CommandTypeMainParts.SEND_MESSAGE);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        commandType.addField(CommandTypesFieldNames.MESSAGE);
        Command.addCommandType(commandType);
    }

    private static void changeAvatar() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.CHANGE_AVATAR);
        commandType.setMainPart(CommandTypeMainParts.CHANGE_AVATAR);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        commandType.addField(CommandTypesFieldNames.NEW_AVATAR);
        Command.addCommandType(commandType);
    }

    private static void changePassword() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.CHANGE_PASSWORD);
        commandType.setMainPart(CommandTypeMainParts.CHANGE_PASSWORD);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        commandType.addField(CommandTypesFieldNames.CURRENT_PASSWORD);
        commandType.addField(CommandTypesFieldNames.NEW_PASSWORD);
        Command.addCommandType(commandType);
    }

    private static void changeNickname() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.CHANGE_NICKNAME);
        commandType.setMainPart(CommandTypeMainParts.CHANGE_NICKNAME);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        commandType.addField(CommandTypesFieldNames.NEW_NICKNAME);
        Command.addCommandType(commandType);
    }

    private static void logout() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.LOGOUT);
        commandType.setMainPart(CommandTypeMainParts.LOGOUT);
        commandType.addField(CommandTypesFieldNames.TOKEN);
        Command.addCommandType(commandType);
    }

    private static void signUp() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.SIGN_UP);
        commandType.setMainPart(CommandTypeMainParts.SIGNUP);
        commandType.addField(CommandTypesFieldNames.USERNAME);
        commandType.addField(CommandTypesFieldNames.NICKNAME);
        commandType.addField(CommandTypesFieldNames.PASSWORD);
        Command.addCommandType(commandType);
    }

    private static void login() {
        CommandType commandType = new CommandType();
        commandType.setName(CommandTypeNames.LOGIN);
        commandType.setMainPart(CommandTypeMainParts.LOGIN);
        commandType.addField(CommandTypesFieldNames.USERNAME);
        commandType.addField(CommandTypesFieldNames.PASSWORD);
        Command.addCommandType(commandType);
    }
}
