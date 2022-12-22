package src;
import java.util.Scanner;

public class UserInteraction {
    Scanner setupPlayersScanner;
    Scanner moveScanner;
    Scanner newGameScanner;
    //Can I set 1 scanner for both ?
    View printRequests;

    public UserInteraction(){
        setupPlayersScanner = new Scanner(System.in);
        moveScanner = new Scanner(System.in);
        newGameScanner = new Scanner(System.in);
        printRequests = new View();
    }

    protected Player setupPlayers(String playerNth, String pSymbol){
        String player;
        do {
            this.printRequests.playersTypeChoice(playerNth, pSymbol);
            player = this.setupPlayersScanner.nextLine();
        }while (!player.equals("1") && !player.equals("2"));
        //Need close scanner but don't know where
        return (player.equals("1")) ? new HumanPlayer(pSymbol) : new ArtificialPlayer(pSymbol);
    }

    protected String askPlayerMove(String coordinate){
        this.printRequests.playerMoveChoice(coordinate);
        return moveScanner.nextLine();
        //Need close scanner but don't know where
    }

    protected boolean playAgain(){
        boolean correctEntry = false;
        String otherGame;
        do {
            this.printRequests.displayAskNewGame();
            otherGame = newGameScanner.nextLine();
            if (otherGame.equalsIgnoreCase("Y") || otherGame.equalsIgnoreCase("N")) {
                correctEntry = true;

            }
        } while (!correctEntry);
        return (otherGame.equalsIgnoreCase("Y") ? true : false);
    }


}
