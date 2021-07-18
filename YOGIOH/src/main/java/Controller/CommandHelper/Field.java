package Controller.CommandHelper;

public class Field {
    boolean doesHaveArgument = true;
    String name, argument;
    String regEx = ".*";

    public Field(String name, String argument) {
        this.name = name;
        this.argument = argument;
    }

    public Field(String name) {
        this.name = name;
    }

    public String getRegEx() {
        return regEx;
    }

    public void setRegEx(String regEx) {
        this.regEx = regEx;
    }

    public boolean isDoesHaveArgument() {
        return doesHaveArgument;
    }

    public void setDoesHaveArgument(boolean doesHaveArgument) {
        this.doesHaveArgument = doesHaveArgument;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArgument() {
        if (doesHaveArgument) {
            return argument;
        }
        return null;
    }

    public void setArgument(String argument) throws Exception {
        if (!doesHaveArgument) {
            System.out.println("In field \"+\n" +
                    "                    this.getName()+\" : \\nThis Field can't have ARGUMENT but setArgument method of it has been called!");
            throw new Exception("In field "+
                    this.getName()+" : \nThis Field can't have ARGUMENT but setArgument method of it has been called!");
        }
        if (doesHaveArgument && argument.matches(regEx)) {
            this.argument = argument;
        }
    }

    @Override
    public String toString() {
        if (doesHaveArgument) {
            return "{" +
                    "name = \"" + name + '\"' +
                    ", argument = \"" + argument + '\"' +
                    '}';
        } else return "{name = "+name+"}";
    }
}
