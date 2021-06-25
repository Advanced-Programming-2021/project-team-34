package Controller.Menus;

import Model.User;

public class ScoreBoardMenu {
    public String showRankings(){
        StringBuilder scoreBoard = new StringBuilder();
        for(User user : User.users) {
            scoreBoard.append(user.getRank()).append("_  ").append(user.getNickname()).append(":  ").append(user.getHighScore());
        }
        return scoreBoard.toString();
    }
}
