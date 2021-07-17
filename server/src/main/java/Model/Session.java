package Model;

import Exceptions.NoSuchTokenException;

import java.util.ArrayList;

public class Session {
    Token token;
    String username;
    private static ArrayList<Session> sessions = new ArrayList<>();

    public Session(Token token, String username) {
        deleteSessionOfUsername(username);
        this.token = token;
        this.username = username;
        sessions.add(this);
    }

    public Session(String username) {
        deleteSessionOfUsername(username);
        token = new Token();
        this.username = username;
        sessions.add(this);
    }

    public static String getUsernameOfToken(Token token) throws NoSuchTokenException {
        for (Session session :
                sessions) {
            if (session.token.equals(token)) {
                return session.username;
            }
        } throw new NoSuchTokenException();
    }

    public static String getUsernameOfToken(String token) throws NoSuchTokenException {
        for (Session session :
                sessions) {
            if (session.token.equals(token)) {
                return session.username;
            }
        } throw new NoSuchTokenException();
    }

    private static Session getSessionOfUsername(String username) {
        for (Session session :
                sessions) {
            if (session.username.equals(username)) {
                return session;
            }
        }
        return null;
    }

    private static void deleteSessionOfUsername(String username) {
        Session session = getSessionOfUsername(username);
        if (session != null) {
            sessions.remove(session);
        }
    }

    // getters and setters

    public Token getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }
}
