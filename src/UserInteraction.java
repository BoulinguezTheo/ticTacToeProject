package src;
import java.util.Scanner;

public class UserInteraction {
    Scanner setupPlayersScanner;
    Scanner moveScanner;
    //Can I set 1 scanner for both ?
    View printRequests;

    public UserInteraction(){
        setupPlayersScanner = new Scanner(System.in);
        moveScanner = new Scanner(System.in);
        printRequests = new View();
    }
    protected Player setupPlayers(String playerNth, String pSymbol){
        this.printRequests.playersTypeChoice(playerNth, pSymbol);
        int player = this.setupPlayersScanner.nextInt();
        //Need close scanner but don't know where
        return (player == 1) ? new HumanPlayer(pSymbol) : new ArtificialPlayer(pSymbol);
    }

    protected int askPlayerMove(String coordinate){
        this.printRequests.playerMoveChoice(coordinate);
        return moveScanner.nextInt();
        //Need close scanner but don't know where
    }
}
