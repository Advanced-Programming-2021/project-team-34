package Controller;

import Controller.CommandHelper.Command;
import Exceptions.NoSuchIDException;
import FinalStrings.CommandTypeMainParts;
import FinalStrings.CommandTypeNames;
import FinalStrings.CommandTypesFieldNames;
import FinalStrings.Results;
import Model.Message;
import Model.Session;

public class RequestHandler {
    public static String handleRequest(String request) {
        Command command = new Command(request);
        String typeOfCommand = command.getType();
        switch (typeOfCommand) {
            case CommandTypeNames.SIGN_UP:
                return signUp(command);
            case CommandTypeNames.SEND_MESSAGE:
                return sendMessage(command);
            case CommandTypeNames.REPLY_MESSAGE:
                return replyMessage(command);
            default:
                return Results.INVALID_SYNTAX_OF_REQUEST;
        }
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
        String username = Session.getUsernameOfToken(tokenCode);
        String idOfMessageToReplyString = command.getField(CommandTypesFieldNames.REPLY_ON);
        return Doer.replyMessage(messageText , username , idOfMessageToReplyString);
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
     * This method is for signing up and creating new account and CommandType is :
     * <p>
     *     CommandType name : sign up
     *     syntax :
     *     signup --username < username> --nickname < nickname> --password < password>
     * </p>
     */
    private static String signUp(Command command) {
        String username = command.getField(CommandTypesFieldNames.USERNAME);
        String nickname = command.getField(CommandTypesFieldNames.NICKNAME);
        String password = command.getField(CommandTypesFieldNames.PASSWORD);
        String result = Doer.signUp(username , password , nickname);
        return result;
    }
}
