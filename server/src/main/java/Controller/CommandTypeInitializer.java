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
        changeNickname();// change nickname --token <token> --newNickname <newNickname>
        changePassword();// change password --token <token> --currentPassword <currentPassword> --newPassword <newPassword>
        changeAvatar();// change avatar --token <token> --newAvatar <newAvatar> // newAvatar is a number!
        sendMessage();// send message --token <token> --message <message> // DON'T FORGET '"' BEFORE AND AFTER THE MESSAGE!
        replyMessage();// send message --token <token> --message <message> --replyOn <repliedMessageID>
        increaseCoin();// increase coin --token <token> --value <value>
        getMessages(); // get messages
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
