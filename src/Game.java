import java.util.Random;

public class Game {
    private Player winner;
    Player player1, player2 , currentPlayer;
    int turn = 1;//1 - 2
    Board board;
    public Game(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        player1.setLifePoint();
        player2.setLifePoint();
        this.board = new Board(player1,player2);
    }

    public void run(){
        while (player1.lifePoint>0 && player2.lifePoint>0){
            if(turn==1){
                currentPlayer = player1;
            }else (turn==2){
                currentPlayer = player2;
            }

            drawPhase(currentPlayer);
            standbyPhase(currentPlayer);
            mainPhase1(currentPlayer);
            battlePhase(currentPlayer);
            mainPhase2(currentPlayer);
            endPhase(currentPlayer);
        }
    }
    Card drawCard;
    public void drawPhase(Player player){
        Random random = new Random(System.currentTimeMillis());
        int r = random.nextInt(100);
        if(player.getUsername().equals(player1.getUsername())||player==player1){
            if(board.player1Hand.size()<6){
                drawCard = board.player1RemainedDeck.get(r%board.player1RemainedDeck.size());
                board.player1Hand.add(drawCard);
                board.player1RemainedDeck.remove(r%board.player1RemainedDeck.size());
            }
        }else {
            if(board.player2Hand.size()<6){
                drawCard = board.player2RemainedDeck.get(r%board.player2RemainedDeck.size());
                board.player2Hand.add(drawCard);
                board.player2RemainedDeck.remove(r%board.player2RemainedDeck.size());
            }
        }

    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
