package Exceptions;

import java.io.IOException;

public class NoMonsterWithThisNameException extends IOException {
    /**
     * Constructs an {@code IOException} with the specified detail message.
     *
     * @param monsterName
     */
    public NoMonsterWithThisNameException(String monsterName) {
        super("no monster with name \""+monsterName+"\" found!");
    }
}
