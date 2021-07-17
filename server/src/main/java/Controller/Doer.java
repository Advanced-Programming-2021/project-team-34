package Controller;

import Controller.CommandHelper.Command;
import Exceptions.DuplicateNicknameException;
import Exceptions.DuplicateUsernameException;
import Exceptions.NoSuchIDException;
import FinalStrings.Results;
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
        String username = Session.getUsernameOfToken(tokenCode);
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
            return Results.INVALID_INT_ID;
        }
    }

    public static String getMessages() {
        return Message.getAllMessagesString();
    }
}
