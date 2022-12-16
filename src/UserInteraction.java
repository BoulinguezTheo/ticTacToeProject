package src;
import java.util.Scanner;

public class UserInteraction {
    Scanner setupPlayersScanner;
    Scanner moveScanner;

    public UserInteraction(){
        setupPlayersScanner = new Scanner(System.in);
        moveScanner = new Scanner(System.in);
    }
    protected Player setupPlayers(String playerNth, String pSymbol){
        System.out.println("-Press 1 for a human player");
        System.out.println("-Press 2 for an artificial player");
        System.out.print("Setup " + playerNth +" player: ");
        int player = this.setupPlayersScanner.nextInt();
//        setupPlayersScanner.close(); //BUG: CLOSE PROGRAM
        return (player == 1) ? new HumanPlayer(pSymbol) : new ArtificialPlayer(pSymbol);
    }

    protected int askPlayerMove(String coordinate){
        System.out.print("Enter" + coordinate + ": ");
        return moveScanner.nextInt();
    }
}
