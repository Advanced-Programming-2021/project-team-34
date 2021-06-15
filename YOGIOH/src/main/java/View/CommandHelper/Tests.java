package View.CommandHelper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    @Test
    public void testCommand() {
        CommandType loginCommandType = new CommandType();
        loginCommandType.setMainPart("login user");
        loginCommandType.setName("login");
        loginCommandType.addField("username");
        loginCommandType.addField("password");

        CommandType registerCommandType = new CommandType();
        registerCommandType.setName("register");
        registerCommandType.setMainPart("register user");
        registerCommandType.addField("username");
        registerCommandType.addField("password");

        Command.addCommandType(loginCommandType);
        Command.addCommandType(registerCommandType);

        Command command = new Command("login user username password password username");
        if (command.getType().equals("login")) {
            assertEquals("password" , command.getField("username"));
            assertEquals("username" , command.getField("password"));
        }
        command = new Command("register user password login username user");
        if (command.getType().equals("register")) {
            assertEquals("login" , command.getField("password"));
            assertEquals("user" , command.getField("username"));
        }
    }

    @Test
    public void test2Command() {

    }
}
