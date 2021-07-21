package Controller;

import Controller.CommandHelper.Command;
import Exceptions.NoSuchTokenException;
import FinalStrings.CommandTypeNames;
import FinalStrings.CommandTypesFieldNames;
import FinalStrings.Results;
import Model.Card;
import Model.Session;

public class RequestHandler {
    public static String handleRequest(String request) {
        Command command = new Command(request);
        String typeOfCommand = command.getType();
        switch (typeOfCommand) {
            case CommandTypeNames.SIGN_UP:
                return signUp(command);
            case CommandTypeNames.LOGIN:
                return login(command);
            case CommandTypeNames.LOGOUT:
                return logout(command);
            case CommandTypeNames.CHANGE_NICKNAME:
                return changeNickname(command);
            case CommandTypeNames.CHANGE_PASSWORD:
                return changePassword(command);
            case CommandTypeNames.SEND_MESSAGE:
                return sendMessage(command);
            case CommandTypeNames.REPLY_MESSAGE:
                return replyMessage(command);
            case CommandTypeNames.GET_MESSAGES:
                return getMessages();
            case CommandTypeNames.NEW_GAME_REQUEST:
                return newGameRequest(command);
            case CommandTypeNames.DELETE_GAME_REQUEST:
                return deleteGameRequest(command);
            case CommandTypeNames.SHOW_SCOREBOARD:
                return showScoreboard(command);
            case CommandTypeNames.BUY_CARD:
                return buyCard(command);
            case CommandTypeNames.SELL_CARD:
                return sellCard(command);
            case CommandTypeNames.ADD_CARD:
                return addCard(command);
            case CommandTypeNames.REMOVE_CARD:
                return removeCard(command);
            case CommandTypeNames.FORBID_CARD:
                return forbidCard(command);
            case CommandTypeNames.UN_FORBID_CARD:
                return unForbidCard(command);
            case CommandTypeNames.GET_USER_AVATAR:
                return getUserAvatar(command);
            case CommandTypeNames.GET_USER_INFO:
                return getUserInfo(command);
            case CommandTypeNames.GET_ALL_USERS:
                return getAllUsers();
            case CommandTypeNames.CHANGE_AVATAR:
                return changeAvatar(command);
            case CommandTypeNames.DELETE_MESSAGES:
                return deleteMessage(command);
            default:
                return Results.INVALID_SYNTAX_OF_REQUEST;
        }
    }

    private static String deleteMessage(Command command) {
        String token = command.getField(CommandTypesFieldNames.TOKEN);
        String idOfMessageToDelete = command.getField(CommandTypesFieldNames.ID);
        if (idOfMessageToDelete.matches("\\d+")) {
            return Doer.deleteMessage(token , idOfMessageToDelete);
        } return Results.INVALID_INT_FORMAT;
    }

    private static String changeAvatar(Command command) {
        String token = command.getField(CommandTypesFieldNames.TOKEN);
        String newAvatarNameString = command.getField(CommandTypesFieldNames.NEW_AVATAR);
        if (!newAvatarNameString.matches("\\d+"))
            return Results.INVALID_INT_FORMAT;
        int newAvatarNameInt = Integer.parseInt(newAvatarNameString);
        return Doer.changeAvatar(token , newAvatarNameInt);
    }

    private static String getAllUsers() {
        return Doer.getAllUsers();
    }

    /**
     * This method returns a string which is Json format serialized information about the User.
     * @return a string which is Json format serialized information about the User.
     */
    private static String getUserInfo(Command command) {
        String tokenCode = command.getField(CommandTypesFieldNames.TOKEN);
        return Doer.getUserInfo(tokenCode);
    }

    private static String getUserAvatar(Command command) {
        String username = command.getField(CommandTypesFieldNames.USERNAME);
        return Doer.getUserAvatar(username);
    }

    private static String login(Command command) {
        String username = command.getField(CommandTypesFieldNames.USERNAME);
        String password = command.getField(CommandTypesFieldNames.PASSWORD);
        return Doer.login(username, password);
    }

    private static String signUp(Command command) {
        String username = command.getField(CommandTypesFieldNames.USERNAME);
        String nickname = command.getField(CommandTypesFieldNames.NICKNAME);
        String password = command.getField(CommandTypesFieldNames.PASSWORD);
        return Doer.signUp(username, password, nickname);
    }

    private static String logout(Command command) {
        String tokenName = command.getField(CommandTypesFieldNames.TOKEN);
        return Doer.logout(tokenName);
    }

    private static String changeNickname(Command command) {
        String tokenName = command.getField(CommandTypesFieldNames.TOKEN);
        String newNickname = command.getField(CommandTypesFieldNames.NEW_NICKNAME);
        return Doer.changeNickname(tokenName, newNickname);
    }

    private static String changePassword(Command command) {
        String tokenName = command.getField(CommandTypesFieldNames.TOKEN);
        String currentPassword = command.getField(CommandTypesFieldNames.PASSWORD);
        String newPassword = command.getField(CommandTypesFieldNames.NEW_PASSWORD);
        return Doer.changePassword(tokenName, currentPassword, newPassword);
    }

    /**
     * This method is for sending a Message and the CommandType is :
     * <p>
     *     CommandType name : send message
     *     syntax :
     *     send message --token < token> --message < message>
     * </p>*/
    private static String sendMessage(Command command) {
        String messageText = command.getField(CommandTypesFieldNames.MESSAGE);
        String tokenCode = command.getField(CommandTypesFieldNames.TOKEN);
        return Doer.sendMessage(tokenCode , messageText);
    }

    /**
     * This method is for replying to another Message and CommandType is :
     * <p>
     * CommandType name : reply message
     * syntax :
     * send message --token < token> --message < message> --replyOn < replyOn>
     * </p>
     */
    private static String replyMessage(Command command) {
        String messageText = command.getField(CommandTypesFieldNames.MESSAGE);
        String tokenCode = command.getField(CommandTypesFieldNames.TOKEN);
        String username = null;
        try {
            username = Session.getUsernameOfToken(tokenCode);
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
        String idOfMessageToReplyString = command.getField(CommandTypesFieldNames.REPLY_ON);
        return Doer.replyMessage(messageText , username , idOfMessageToReplyString);
    }

    /**
     * This method is for sending all Messages to the client in form of a String.
     */
    private static String getMessages() {
        return Doer.getMessages();
    }

    /**
     * This method is for requesting a new game request.
     */
    private static String newGameRequest(Command command) {
        String tokenCode = command.getField(CommandTypesFieldNames.TOKEN);
        String roundString = command.getField(CommandTypesFieldNames.ROUND);
        if (roundString.matches("[13]")) {
            int round = Integer.parseInt(roundString);
            return Doer.newGameRequest(tokenCode , round);
        } else {
            return Results.INVALID_INT_FORMAT;
        }
    }

    /**
     * This method deletes the game request of the user related to the given token.
     */
    private static String deleteGameRequest(Command command) {
        String tokenCode = command.getField(CommandTypesFieldNames.TOKEN);
        return Doer.deleteGameRequest(tokenCode);
    }

    /**
     * This method shows the scoreboard of users
     */
    private static String showScoreboard(Command command) {
        String tokenCode = command.getField(CommandTypesFieldNames.TOKEN);
        return Doer.showScoreboard(tokenCode);
    }

    /**
     * This method buys a card for a player
     */
    private static String buyCard(Command command) {
        String cardName = command.getField(CommandTypesFieldNames.CARD_NAME);
        String tokenCode = command.getField(CommandTypesFieldNames.TOKEN);
        return Doer.buyCard(cardName, tokenCode);
    }

    private static String sellCard(Command command) {
        String cardName = command.getField(CommandTypesFieldNames.CARD_NAME);
        String tokenCode = command.getField(CommandTypesFieldNames.TOKEN);
        return Doer.sellCard(cardName, tokenCode);
    }

    private static String addCard(Command command) {
        String cardName = command.getField(CommandTypesFieldNames.CARD_NAME);
        String tokenCode = command.getField(CommandTypesFieldNames.TOKEN);
        return Doer.addCard(cardName, tokenCode);
    }

    private static String removeCard(Command command) {
        String cardName = command.getField(CommandTypesFieldNames.CARD_NAME);
        String tokenCode = command.getField(CommandTypesFieldNames.TOKEN);
        return Doer.removeCard(cardName, tokenCode);
    }

    private static String forbidCard(Command command) {
        String cardName = command.getField(CommandTypesFieldNames.CARD_NAME);
        String tokenCode = command.getField(CommandTypesFieldNames.TOKEN);
        return Doer.forbidCard(cardName, tokenCode);
    }

    private static String unForbidCard(Command command) {
        String cardName = command.getField(CommandTypesFieldNames.CARD_NAME);
        String tokenCode = command.getField(CommandTypesFieldNames.TOKEN);
        return Doer.unForbidCard(cardName, tokenCode);
    }
}
