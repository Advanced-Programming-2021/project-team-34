package Model;

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

    public static String getUsernameOfToken(Token token) {
        for (Session session :
                sessions) {
            if (session.token.equals(token)) {
                return session.username;
            }
        } return null;
    }

    public static String getUsernameOfToken(String token) {
        for (Session session :
                sessions) {
            if (session.token.equals(token)) {
                return session.username;
            }
        } return null;
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
}
