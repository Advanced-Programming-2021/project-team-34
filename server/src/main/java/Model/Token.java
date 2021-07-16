package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Token {
    private String tokenCode;

    public Token() {
        tokenCode = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj instanceof Token) && ((Token) obj).tokenCode.equals(this.tokenCode))
            return true;
        return (obj instanceof String) && (((String) obj).equals(tokenCode));
    }

    @Override
    public String toString() {
        return tokenCode;
    }
}
