package Exceptions;

public class NoSuchUsernameException extends Exception {
    public NoSuchUsernameException() {
        super("NoSuchUserException");
    }

    public NoSuchUsernameException(String username) {
        super("NoSuchUserException --> username : "+username);
    }
}
