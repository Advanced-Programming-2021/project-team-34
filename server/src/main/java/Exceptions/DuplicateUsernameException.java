package Exceptions;

public class DuplicateUsernameException extends Exception {
    public DuplicateUsernameException(String username) {
        super("DuplicateUsernameException : There's already a user with username @"+username);
    }

    public DuplicateUsernameException() {
        super("DuplicateUsernameException");
    }
}
