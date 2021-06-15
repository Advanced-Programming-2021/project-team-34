package View.CommandHelper;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Command {
    String input;
    String type;
    CommandType commandType;
    private static ArrayList<CommandType> validCommandTypes = new ArrayList<CommandType>();
    public Command(String input) {
        this.input = input;
        determineType();
    }

    public String getField(String fieldName) {
        if (commandType == null) {
            System.out.println("In this command , commandType is null!!!!!!!");
            return "In this command , commandType is null!!!!!!!";
        }
        Field field = commandType.getField(fieldName);
        if (field == null) {
            return "There is no field with name : \""+fieldName+"\" in command \""+input+"\"";
        }
        String argument = field.getArgument();
        if (argument == null) {
            return "This field has no argument";
        }
        return argument;
    }

    public String getType() {
        return type;
    }

    private void determineType() {
        for (CommandType commandType :
                validCommandTypes) {
            if (commandType.checkIfMatches(input)){
                type = commandType.getName();
                this.commandType = commandType;
                return;
            }
        }
        type = "invalid type";
    }

    private static CommandType getCommandTypeByName(String name) {
        for (CommandType commandType :
                validCommandTypes) {
            if (commandType.getName().equals(name)) {
                return commandType;
            }
        }
        return null;
    }
    public static void addCommandType(CommandType commandTypeToAdd) {
        validCommandTypes.add(commandTypeToAdd);
    }
    public static void setValidCommandTypes(ArrayList<CommandType> validCommandTypes) {
        Command.validCommandTypes = validCommandTypes;
    }
    public static void clearValidCommandTypes() {
        validCommandTypes.clear();
    }
}
