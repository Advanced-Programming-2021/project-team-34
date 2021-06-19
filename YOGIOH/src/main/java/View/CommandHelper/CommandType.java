package View.CommandHelper;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CommandType {
    String name;
    String mainPart;
    boolean canHaveSpaceAtFirst = true, isCanHaveSpaceAtLast = true, canHaveMoreThanOneSpaceBetweenFields = true;
    ArrayList<Field> fields = new ArrayList<Field>();
    ArrayList<String> regexes = new ArrayList<String>();
    public void addField(String name) {
        Field field = new Field(name);
        fields.add(field);
    }
    public Field getField(String name) {
        for (Field field :
                fields) {
            if (field.getName().equals(name)) {
                return field;
            }
        }
        return null;
    }
//    private String makeRegEx(ArrayList<Field>) {
//
//    }
    public boolean checkIfMatches(String input) {
        input = deleteWhiteSpaces(input);
        String[] words = input.split(" ");
        String[] mainPartWords = mainPart.split(" ");
        int currentIndex = 0;
        if (!mainPart.equals("") && !checkMatch(words, mainPartWords, 0)) {
//            System.out.println("It seems not to match! ... mainPart = \""+mainPart+"\" and " +
//                    "mainPartWords.length = "+mainPartWords.length+" and the first element of that is \""+mainPartWords[0]+"\"!");
            return false;
        }
//        System.out.println("It seems to match! ...");
        if (!mainPart.equals("")) {
            currentIndex += mainPartWords.length;
        }
        ArrayList<Field> remainedFields = (ArrayList<Field>) fields.clone();
        boolean matched;
        for (int i = 0; i < fields.size(); i++) {
            matched = false;
            for (Field field :
                    remainedFields) {
                if (checkMatch(words, field.name, currentIndex)) {
                    matched = true;
                    currentIndex += deleteWhiteSpaces(field.getName()).split(" ").length;
                    if (field.doesHaveArgument) {
                        try {
                            field.setArgument(words[currentIndex]);
                        } catch (Exception e) {
                        }
                        currentIndex++;
                    } else {
                    }
                    remainedFields.remove(field);
                    break;
                }
            }
            if (!matched) {
//                System.out.println("In round "+i+" , didn't match any field");
                return false;
            }
        }
        if (currentIndex >= words.length) {
            return true;
        }
        return false;
    }
    private static String deleteWhiteSpaces(String str){
        char[] chars = str.toCharArray();
        char[] ansch = new char[str.length()];
        int i = 0, j = 0;
        for (i = 0; i < chars.length; i++) {
            if (chars[i] != ' ' || (i > 0 && chars[i-1] != ' ')) {
                ansch [j] = chars[i];
                j++;
            }
        }
        if (ansch[j-1] == ' ') {
            ansch[j-1] = '\0';
            j--;
        }
        String ans = "";
        for (char ch :
                ansch) {
            if (ch != '\0') {
                ans += ch;
            }
        }
        return ans;
    }

    private static boolean checkMatch(String[] firstString, String secondString , int startOfFirstString) {
        return checkMatch(firstString, deleteWhiteSpaces(secondString).split(" "),startOfFirstString);
    }

    private static boolean checkMatch(String[] firstString, String[] secondString, int startOfFirstString) {
        int currentIndex = 0;
        if (startOfFirstString+secondString.length-1>=firstString.length) {
            return false;
        }
        for (currentIndex = 0; currentIndex < secondString.length; currentIndex++) {
            if (!firstString[startOfFirstString+currentIndex].equals(secondString[currentIndex])) {
                return false;
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.equals("invalid type")) {
            this.name = name;
        }
    }

    public String getMainPart() {
        return mainPart;
    }

    public void setMainPart(String mainPart) {
        this.mainPart = mainPart;
    }

    public boolean isCanHaveSpaceAtFirst() {
        return canHaveSpaceAtFirst;
    }

    public void setCanHaveSpaceAtFirst(boolean canHaveSpaceAtFirst) {
        this.canHaveSpaceAtFirst = canHaveSpaceAtFirst;
    }

    public boolean isCanHaveSpaceAtLast() {
        return isCanHaveSpaceAtLast;
    }

    public void setCanHaveSpaceAtLast(boolean canHaveSpaceAtLast) {
        isCanHaveSpaceAtLast = canHaveSpaceAtLast;
    }

    public boolean isCanHaveMoreThanOneSpaceBetweenFields() {
        return canHaveMoreThanOneSpaceBetweenFields;
    }

    public void setCanHaveMoreThanOneSpaceBetweenFields(boolean canHaveMoreThanOneSpaceBetweenFields) {
        this.canHaveMoreThanOneSpaceBetweenFields = canHaveMoreThanOneSpaceBetweenFields;
    }

    @Test
    public void testDeterminingFieldsInCheckIfMatches() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("login user");
        commandType.addField("username");
        commandType.addField("password");
        boolean ans2543 = commandType.checkIfMatches("login user username mahdi password 1234");
        String username = commandType.getField("username").getArgument();
        String password = commandType.getField("password").getArgument();
        assertEquals("mahdi" , username);
        assertEquals("1234" , password);
    }

    @Test
    public void testCheckIfMatches() {
        CommandType commandType = new CommandType();
        commandType.setMainPart("login user");
        commandType.addField("username");
        commandType.addField("password");
        boolean ans1 = commandType.checkIfMatches("login user username mahdi password 1234");
        boolean ans2 = commandType.checkIfMatches("login  user  password 1234 username mahdi");
        boolean ans3 = commandType.checkIfMatches("login user password username user pass");
        boolean ans4 = commandType.checkIfMatches("  login user   password username   username password");
        boolean ans5 = commandType.checkIfMatches(" login user username password username username");
        assertTrue(ans1);
        assertTrue(ans2);
        assertFalse(ans3);
        assertTrue(ans4);
        assertFalse(ans5);
    }

    @Test
    public void test2CheckIfMatches() {
        CommandType changePasswordCommandType = new CommandType();
        changePasswordCommandType.setMainPart("change profile");
        changePasswordCommandType.addField("password");
        changePasswordCommandType.getField("password").setDoesHaveArgument(false);
        changePasswordCommandType.addField("current");
        changePasswordCommandType.addField("new");

        CommandType changeNicknameCommandType = new CommandType();
        changeNicknameCommandType.setMainPart("change profile");
        changeNicknameCommandType.addField("nickname");

        String changePasswordCommandString = "change profile current nickname new 4321 password";
        String changeNicknameCommandString = "change profile nickname password";

        assertTrue(changeNicknameCommandType.checkIfMatches(changeNicknameCommandString));
        assertFalse(changeNicknameCommandType.checkIfMatches(changePasswordCommandString));
        assertTrue(changePasswordCommandType.checkIfMatches(changePasswordCommandString));
        assertFalse(changePasswordCommandType.checkIfMatches(changeNicknameCommandString));
    }

    @Test
    public void testCheckMatch() {
        // login  user   username   mahdi   password   1234
        String[] firstString = deleteWhiteSpaces("  login  user   username    mahdi    password   1234  ").split(" ");
        boolean ans1 = checkMatch(firstString, "login user", 0);
        boolean ans2 = checkMatch(firstString, "username", 2);
        boolean ans3 = checkMatch(firstString, "username" , 4);
        boolean ans4 = checkMatch(firstString, "  username ", 2);
        boolean ans5 = checkMatch(firstString, "user name", 2);
        boolean ans6 = checkMatch(firstString, "log in user username mahdi password 1234", 0);
        assertTrue(ans1);
        assertTrue(ans2);
        assertFalse(ans3);
        assertTrue(ans4);
        assertFalse(ans5);
        assertFalse(ans6);
    }

    @Test
    public void testDeleteWhiteSpace() {
        String ans1 = deleteWhiteSpaces("salam");
        String ans2 = deleteWhiteSpaces("chetori joooone del!");
        String ans3 = deleteWhiteSpaces("    login user   with    username    mahdi ");
        assertEquals("salam" , ans1);
        assertEquals("chetori joooone del!" , ans2);
        assertEquals("login user with username mahdi" , ans3);
    }
}
