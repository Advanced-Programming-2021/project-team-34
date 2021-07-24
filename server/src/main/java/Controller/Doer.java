package Controller;

import Exceptions.*;
import FinalStrings.Results;
import Model.*;
import com.google.gson.Gson;


/**
 * This class does functions of commands.
 */
public class Doer {

    public static String signUp(String username, String password, String nickname) {
        try {
            User user = new User(username, password, nickname);
            Session session = new Session(username);
            return "success token " + session.getToken();
        } catch (DuplicateUsernameException e) {
            return Results.USERNAME_IS_ALREADY_REGISTERED;
        } catch (DuplicateNicknameException e) {
            return Results.NICKNAME_IS_ALREADY_REGISTERED;
        }
    }

    public static synchronized String login(String username, String password) {
        try {
            if (User.getUserByUsername(username) == null) {
                throw new WrongPasswordException();
            } else if (!User.checkPassword(username, password)) {
                throw new WrongPasswordException();
            } else {
                Session session = new Session(new Token(), username);
                    return Results.SUCCESS+" token "+session.getToken();
            }
        } catch (WrongPasswordException e) {
            return Results.USERNAME_AND_PASSWORD_DO_NOT_MATCH;
        }
    }

    public static synchronized String logout(String tokenName) {
        try {
            String username = Session.getUsernameOfToken(tokenName);
            if (username == null) throw new NoSuchTokenException();
            Session.deleteSessionOfUsername(username);
            return Results.SUCCESS;
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
    }

    public static String changeNickname(String tokenName, String newNickname) {
        try {
            String username = Session.getUsernameOfToken(tokenName);
            if (username == null) throw new NoSuchTokenException();
            User user = User.getUserByUsername(username);
            user.setNickname(newNickname);
            return Results.SUCCESS;
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
    }

    public static String changePassword(String tokenName, String currentPassword, String newPassword) {
        try {
            String username = Session.getUsernameOfToken(tokenName);
            if (username == null) throw new NoSuchTokenException();
            User user = User.getUserByUsername(username);
            if (user.changePassword(currentPassword, newPassword)) return Results.SUCCESS;
            else throw new WrongPasswordException();
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        } catch (WrongPasswordException e) {
            return Results.USERNAME_AND_PASSWORD_DO_NOT_MATCH;
        }
    }

    public static String sendMessage(String tokenCode, String messageText) {
        String username = null;
        try {
            username = Session.getUsernameOfToken(tokenCode);
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
        User user = User.getUserByUsername(username);
        if (user == null) {
            return (Results.ERROR);
        }
        Message message = new Message(messageText , username);
        return Results.SUCCESS;
    }

    public static String replyMessage(String messageText, String username, String idOfMessageToReplyString) {
        if (idOfMessageToReplyString.matches("\\d+")) {
            int idOfMessageToReply = Integer.parseInt(idOfMessageToReplyString);
            try {
                Message message = new Message(messageText , username , Message.getMessageById(idOfMessageToReply));
                return Results.SUCCESS;
            } catch (NoSuchIDException e) {
                return Results.INVALID_ID;
            }
        } else {
            return Results.INVALID_INT_FORMAT;
        }
    }

    public static String getMessages() {
        //return Message.getAllMessagesString();
        String string = new Gson().toJson(Message.getMessages());
        return string;
    }

    public static String newGameRequest(String tokenCode, int round) {
        String username;
        try {
            username = Session.getUsernameOfToken(tokenCode);
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
        User user = User.getUserByUsername(username);
        if (user == null) {
            return Results.ERROR;
        }

        GameRequest gameRequest = ((round == 1) ? (GameRequest.ONE_ROUND) : (GameRequest.THREE_ROUND));
        if (user.getGameRequest().equals(GameRequest.NO)) {
            user.setGameRequest(gameRequest);
            return Results.SUCCESS;
        } else {
            return Results.HAS_ALREADY_REQUESTED;
        }
    }

    public static String deleteGameRequest(String tokenCode) {
        String username;
        try {
            username = Session.getUsernameOfToken(tokenCode);
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
        User user = User.getUserByUsername(username);
        if (user == null) {
            return Results.ERROR;
        }
        if (!user.getGameRequest().equals(GameRequest.NO)) {
            return Results.HAS_NO_REQUEST;
        } else {
            user.setGameRequest(GameRequest.NO);
            return Results.SUCCESS;
        }
    }

    public static String showScoreboard(String tokenCode) {
        try {
            String username = Session.getUsernameOfToken(tokenCode);
            User user = User.getUserByUsername(username);
            if (user == null) return Results.ERROR;
            StringBuilder answer = null;
            for (User aUser : User.users) {
                answer.append(aUser.getNickname()).append(" ").append(aUser.getHighScore()).append("\n");
            }
            return answer.toString();
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
    }

    public static String buyCard(String cardName, String tokenCode) {
        try {
            String username = Session.getUsernameOfToken(tokenCode);
            User user = User.getUserByUsername(username);
            if (user == null) return Results.ERROR;
            if (!Shop.getPrices().containsKey(cardName)) return Results.NO_SUCH_CARD_EXISTS;
            if (Shop.getForbidden().contains(cardName)) return Results.FORBIDDEN_CARD;
            if (user.getCoin() < Shop.getPrices().get(cardName)) return Results.NOT_ENOUGH_MONEY;
            if (Shop.getAmount().get(cardName) < 1) return Results.THERE_IS_NO_CARD;
            Shop.buyCard(cardName, user);
            return "success";
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
    }

    public static String sellCard(String cardName, String tokenCode) {
        try {
            String username = Session.getUsernameOfToken(tokenCode);
            User user = User.getUserByUsername(username);
            if (user == null) return Results.ERROR;
            if (!Shop.getPrices().containsKey(cardName)) return Results.NO_SUCH_CARD_EXISTS;
            if (!user.getCards().containsKey(Card.getAllCards().get(cardName)) ||
                    user.getCards().get(Card.getAllCards().get(cardName)) < 1) return Results.THERE_IS_NO_CARD;
            Shop.sellCard(cardName, user);
            return "success";
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
    }

    public static String addCard(String cardName, String tokenCode) {
        try {
            String username = Session.getUsernameOfToken(tokenCode);
            User user = User.getUserByUsername(username);
            if (user == null) return Results.ERROR;
            if (!username.equals(Shop.getAdminUsername())) return Results.NO_ACCESS;
            if (!Shop.getPrices().containsKey(cardName)) return Results.NO_SUCH_CARD_EXISTS;
            Shop.increaseCard(cardName, 1);
            return "success";
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
    }

    public static String removeCard(String cardName, String tokenCode) {
        try {
            String username = Session.getUsernameOfToken(tokenCode);
            User user = User.getUserByUsername(username);
            if (user == null) return Results.ERROR;
            if (!username.equals(Shop.getAdminUsername())) return Results.NO_ACCESS;
            if (!Shop.getPrices().containsKey(cardName)) return Results.NO_SUCH_CARD_EXISTS;
            Shop.decreaseCard(cardName, 1);
            return "success";
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
    }

    public static String forbidCard(String cardName, String tokenCode) {
        try {
            String username = Session.getUsernameOfToken(tokenCode);
            User user = User.getUserByUsername(username);
            if (user == null) return Results.ERROR;
            if (!username.equals(Shop.getAdminUsername())) return Results.NO_ACCESS;
            if (!Shop.getPrices().containsKey(cardName)) return Results.NO_SUCH_CARD_EXISTS;
            Shop.getForbidden().add(cardName);
            return "success";
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
    }

    public static String unForbidCard(String cardName, String tokenCode) {
        try {
            String username = Session.getUsernameOfToken(tokenCode);
            User user = User.getUserByUsername(username);
            if (user == null) return Results.ERROR;
            if (!username.equals(Shop.getAdminUsername())) return Results.NO_ACCESS;
            if (!Shop.getPrices().containsKey(cardName)) return Results.NO_SUCH_CARD_EXISTS;
            Shop.getForbidden().remove(cardName);
            return "success";
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
    }

    public static String getUserAvatar(String username) {
        User user = User.getUserByUsername(username);
        if (user == null) {
            return Results.INVALID_USERNAME;
        }
        return user.getAvatarName();
    }

    public static String getUserInfo(String tokenCode) {
        String username;
        try {
            username = Session.getUsernameOfToken(tokenCode);
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
        User user = User.getUserByUsername(username);
        if (user == null) {
            return Results.ERROR;
        }
        return new Gson().toJson(user);
    }

    public static String getAllUsers() {
        return new Gson().toJson(User.getUsers());
    }

    public static String changeAvatar(String token, int newAvatarNameInt) {
        String username;
        try {
            username = Session.getUsernameOfToken(token);
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
        User user = User.getUserByUsername(username);
        if (user == null) {
            return Results.ERROR;
        }
        user.setAvatarInt(newAvatarNameInt);
        return Results.SUCCESS;
    }

    public static String deleteMessage(String token, String idOfMessageToDelete) {
        String username = null;
        try {
            username = Session.getUsernameOfToken(token);
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
        User user = User.getUserByUsername(username);
        if (user == null) {
            return Results.ERROR;
        }
        Message message = null;
        try {
            message = Message.getMessageById(Integer.parseInt(idOfMessageToDelete));
        } catch (NoSuchIDException e) {
            return Results.INVALID_ID;
        }
        if (!message.getUsernameOfSender().equals(username)) {
            return Results.YOU_DO_NOT_HAVE_ACCESS_TO_DO_THIS;
        }
        message.delete();
        return Results.SUCCESS;
    }

//    public static String gameSurrender(String token) {
//
//    }

    public static String gameDirectAttack(String token) {
        String username = null;
        try {
            username = Session.getUsernameOfToken(token);
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
        User user = User.getUserByUsername(username);
        Player player = user.getPlayer();
        return player.getGame().directAttack();
    }

    public static String gameAttackToAMonster(String monsterNumber,String token) {
        String username = null;
        try {
            username = Session.getUsernameOfToken(token);
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
        User user = User.getUserByUsername(username);
        Player player = user.getPlayer();
        return player.getGame().attackToAMonster(monsterNumber);
    }

    public static String gameFlipSummon(String token) {
        String username = null;
        try {
            username = Session.getUsernameOfToken(token);
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
        User user = User.getUserByUsername(username);
        Player player = user.getPlayer();
        if (player.getGame().flipSummon()) return Results.SUCCESS;
        else return player.getGame().getError();
    }

    public static String gameSetPosition(String position, String token) {
        String username = null;
        try {
            username = Session.getUsernameOfToken(token);
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
        User user = User.getUserByUsername(username);
        Player player = user.getPlayer();
        if (player.getGame().setPosition(position)) return Results.SUCCESS;
        else return player.getGame().getError();
    }

    public static String gameSetCard(String token) {
        String username = null;
        try {
            username = Session.getUsernameOfToken(token);
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
        User user = User.getUserByUsername(username);
        Player player = user.getPlayer();
        if (player.getGame().set()) return Results.SUCCESS;
        else return player.getGame().getError();
    }

    public static String gameNextPhase(String token) {
        String username = null;
        try {
            username = Session.getUsernameOfToken(token);
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
        User user = User.getUserByUsername(username);
        Player player = user.getPlayer();
        return player.getGame().nextPhase();
    }

    public static String gameSummon(String token) {
        String username = null;
        try {
            username = Session.getUsernameOfToken(token);
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
        User user = User.getUserByUsername(username);
        Player player = user.getPlayer();
        if (player.getGame().summon()) return Results.SUCCESS;
        else return player.getGame().getError();
    }

    public static String gameSelectCard(String cardAddress, String token) {
        String username = null;
        try {
            username = Session.getUsernameOfToken(token);
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
        User user = User.getUserByUsername(username);
        Player player = user.getPlayer();
        if (player.getGame().select(cardAddress)) return Results.SUCCESS;
        else return player.getGame().getError();
    }

    public static String gameUnselectCard(String token) {
        String username = null;
        try {
            username = Session.getUsernameOfToken(token);
        } catch (NoSuchTokenException e) {
            return Results.INVALID_TOKEN;
        }
        User user = User.getUserByUsername(username);
        Player player = user.getPlayer();
        if (player.getGame().unselect()) return Results.SUCCESS;
        else return player.getGame().getError();
    }
}
