package Controller;

import Exceptions.DuplicateNicknameException;
import Exceptions.DuplicateUsernameException;
import Exceptions.NoSuchIDException;
import Exceptions.NoSuchTokenException;
import FinalStrings.Results;
import Model.GameRequest;
import Model.Message;
import Model.Session;
import Model.User;

/**
 * This class does functions of commands.
 */
public class Doer {

    public static String signUp(String username, String password, String nickname) {
        try {
            User user = new User(username, password, nickname);
            Session session = new Session(username);
            return "success token "+session.getToken();
        } catch (DuplicateUsernameException | DuplicateNicknameException e) {
            return e.getMessage();
        }
    }

    public static String sendMessage(String tokenCode, String messageText) {
        String username = null;
        try {
            username = Session.getUsernameOfToken(tokenCode);
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
        User user = User.getUserByUsername(username);
        if (user == null) {
            return (Results.ERROR);
        }
        Message message = new Message(messageText , username);
        return Results.SUCCESS;
    }

    public static String replyMessage(String messageText, String username, String idOfMessageToReplyString) {
        if (idOfMessageToReplyString.matches("\\d+")) {
            int idOfMessageToReply = Integer.parseInt(idOfMessageToReplyString);
            try {
                Message message = new Message(messageText , username , Message.getMessageById(idOfMessageToReply));
                return Results.SUCCESS;
            } catch (NoSuchIDException e) {
                return Results.INVALID_ID;
            }
        } else {
            return Results.INVALID_INT_FORMAT;
        }
    }

    public static String getMessages() {
        return Message.getAllMessagesString();
    }

    public static String newGameRequest(String tokenCode, int round) {
        String username;
        try {
            username = Session.getUsernameOfToken(tokenCode);
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
        User user = User.getUserByUsername(username);
        if (user == null) {
            return Results.ERROR;
        }

        GameRequest gameRequest = ((round == 1) ? (GameRequest.ONE_ROUND) : (GameRequest.THREE_ROUND));
        if (user.getGameRequest().equals(GameRequest.NO)) {
            user.setGameRequest(gameRequest);
            return Results.SUCCESS;
        } else {
            return Results.HAS_ALREADY_REQUESTED;
        }
    }

    public static String deleteGameRequest(String tokenCode) {
        String username;
        try {
            username = Session.getUsernameOfToken(tokenCode);
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
        User user = User.getUserByUsername(username);
        if (user == null) {
            return Results.ERROR;
        }
        if (!user.getGameRequest().equals(GameRequest.NO)) {
            return Results.HAS_NO_REQUEST;
        } else {
            user.setGameRequest(GameRequest.NO);
            return Results.SUCCESS;
        }
    }
}
