package Controller;

import Controller.CommandHelper.Command;
import Exceptions.DuplicateNicknameException;
import Exceptions.DuplicateUsernameException;
import Model.User;

/**
 * This class does functions of commands.
 */
public class Doer {

    public static String signUp(String username, String password, String nickname) {
        try {
            User user = new User(username, password, nickname);
            return "success";
        } catch (DuplicateUsernameException | DuplicateNicknameException e) {
            return e.getMessage();
        }
    }
}
