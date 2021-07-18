package Controller;

import Model.GameRequest;
import Model.User;

public class GameRequestsHandler extends Thread {
    @Override
    public void run() {
        doMain();
    }

    private void doMain() {

    }

    /**
     * When this method be called , it checks if there is two users with same GameRequest and returns a two-home array of Users
     *      and null if there are no two users with same GameRequest.
     * @return User[] which is {user1 : User , user2 : User} if there are two users with same GameRequest
     *      and is <b>null</b> if there are no two users with same request.
     */
    private static User[] handle() {
        User aUserWhoHasRequestedOneRoundGame = null;
        User aUserWhoHasRequestedThreeRoundGame = null;
        for (User user :
                User.users) {
            if (user.getGameRequest().equals(GameRequest.ONE_ROUND)) {
                if (aUserWhoHasRequestedOneRoundGame == null) {
                    aUserWhoHasRequestedOneRoundGame = user;
                } else {
                    return new User[]{user, aUserWhoHasRequestedOneRoundGame};
                }
            } else if (user.getGameRequest().equals(GameRequest.THREE_ROUND)) {
                if (aUserWhoHasRequestedThreeRoundGame == null) {
                    aUserWhoHasRequestedThreeRoundGame = user;
                } else {
                    return new User[]{user, aUserWhoHasRequestedThreeRoundGame};
                }
            }
        }
        return null;
    }
}
