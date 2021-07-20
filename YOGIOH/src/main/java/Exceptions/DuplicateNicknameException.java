package Exceptions;

public class DuplicateNicknameException extends Exception {
    public DuplicateNicknameException(String nickname) {
        super("DuplicateNicknameException : There's already a user with nickname \""+nickname+"\"");
    }

    public DuplicateNicknameException() {
        super("DuplicateNicknameException");
    }
}
