package Controller;

import Controller.CommandHelper.Command;
import FinalStrings.CommandTypeMainParts;
import FinalStrings.CommandTypeNames;
import FinalStrings.CommandTypesFieldNames;
import FinalStrings.Results;

public class RequestHandler {
    public static String handleRequest(String request) {
        Command command = new Command(request);
        String typeOfCommand = command.getType();
        switch (typeOfCommand) {
            case CommandTypeNames.SIGN_UP:
                return signUp(command);
            default:
                return Results.INVALID_SYNTAX_OF_REQUEST;
        }
    }

    private static String signUp(Command command) {
        String username = command.getField(CommandTypesFieldNames.USERNAME);
        String nickname = command.getField(CommandTypesFieldNames.NICKNAME);
        String password = command.getField(CommandTypesFieldNames.PASSWORD);
        String result = Doer.signUp(username , password , nickname);
        return result;
    }
}
