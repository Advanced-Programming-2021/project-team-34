...|
CommandHelper for java version 3 by Mahdi Teymoori Anar
name : CommandHelper
language : java
version : 3
author : Mahdi Teymoori Anar

What's new in this version :
{
    * CommandType toString returns name and syntax of the command type e.g :
        CommandType name : login command
        syntax :
        login --username <username> --password <password> --secureMode

    It was the output of :
        CommandType commandType = new CommandType();
        commandType.setName("login command");
        commandType.setMainPart("login");
        commandType.addField("username");
        commandType.addField("password");
        commandType.addField("secureMode" , false);
        assertEquals("CommandType name : login command\nsyntax : \nlogin --username <username>" +
            " --password <password> --secureMode" , commandType+"");
        System.out.println(commandType);

    It can be used to give help for syntax of command Types for example you can show for all CommandTypes you  have defined
        to the user so hi or she can realize the correct format of commands
}
What was new in version 2 :
{
    * supporting arguments for fields between two quotation with SPACE
    (in version 1 arguments couldn't have SPACE character)
    * in CommandType instances you can addField(name , /* new */ boolean doesHaveArgument)
    instead of addField(name) then .getField(name).setDoesHaveArgument(boolean value)
    how ever you can do previous method.
}