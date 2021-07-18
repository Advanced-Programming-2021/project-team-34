package Controller;

import Controller.CommandHelper.Command;
import Controller.CommandHelper.CommandType;

public class CommandTypeInitializer {
    public static void run() {
        Command.clearValidCommandTypes();
        signupResult(); // success token erjooj...

    }

    private static void signupResult() {
        CommandType commandType = new CommandType();
        commandType.setName("signup or login successfully");
        commandType.setMainPart("success");
        commandType.addField("token");
        Command.addCommandType(commandType);
    }
}
