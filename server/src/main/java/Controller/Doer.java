package Controller;

import Exceptions.*;
import FinalStrings.Results;
import Model.*;


/**
 * This class does functions of commands.
 */
public class Doer {

    public static String signUp(String username, String password, String nickname) {
        try {
            User user = new User(username, password, nickname);
            Session session = new Session(username);
            return "success token " + session.getToken();
        } catch (DuplicateUsernameException e) {
            return Results.USERNAME_IS_ALREADY_REGISTERED;
        } catch (DuplicateNicknameException e) {
            return Results.NICKNAME_IS_ALREADY_REGISTERED;
        }
    }

    public static synchronized String login(String username, String password) {
        try {
            if (User.getUserByUsername(username) == null) {
                throw new WrongPasswordException();
            } else if (!User.checkPassword(username, password)) {
                throw new WrongPasswordException();
            } else {
                new Session(new Token(), username);
                    return Results.SUCCESS;
            }
        } catch (WrongPasswordException e) {
            return Results.USERNAME_AND_PASSWORD_DO_NOT_MATCH;
        }
    }

    public static synchronized String logout(String tokenName) {
        try {
            String username = Session.getUsernameOfToken(tokenName);
            if (username == null) throw new NoSuchTokenException();
            Session.deleteSessionOfUsername(username);
            return Results.SUCCESS;
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
    }

    public static String changeNickname(String tokenName, String newNickname) {
        try {
            String username = Session.getUsernameOfToken(tokenName);
            if (username == null) throw new NoSuchTokenException();
            User user = User.getUserByUsername(username);
            user.setNickname(newNickname);
            return Results.SUCCESS;
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
    }

    public static String changePassword(String tokenName, String currentPassword, String newPassword) {
        try {
            String username = Session.getUsernameOfToken(tokenName);
            if (username == null) throw new NoSuchTokenException();
            User user = User.getUserByUsername(username);
            if (user.changePassword(currentPassword, newPassword)) return Results.SUCCESS;
            else throw new WrongPasswordException();
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        } catch (WrongPasswordException e) {
            return Results.USERNAME_AND_PASSWORD_DO_NOT_MATCH;
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
