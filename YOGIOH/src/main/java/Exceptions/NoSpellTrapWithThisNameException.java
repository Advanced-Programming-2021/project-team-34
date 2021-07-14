package Exceptions;

import java.io.IOException;

public class NoSpellTrapWithThisNameException extends IOException {
    public NoSpellTrapWithThisNameException(String name) {
        super("no spell or trap with name \""+name+"\" found!");
    }
}
