/*
 * Nom de classe : UserInteraction
 *
 * Description   : Classe regroupant toutes les fonctions demandant aux utilisateurs d'intéragir avec le programme
 *
 * Version       : 1.0
 *
 * Date          : 02/01/2023
 *
 * Copyright     : moi
 */
package src.vue;
import java.util.Scanner;

import src.model.ArtificialPlayer;
import src.model.Cell;
import src.model.HumanPlayer;
import src.model.Player;

public class UserInteraction {
    Scanner setupPlayersScanner;
    Scanner moveScanner;
    Scanner newGameScanner;
    View printRequests;

    public UserInteraction(){
        setupPlayersScanner = new Scanner(System.in);
        moveScanner = new Scanner(System.in);
        newGameScanner = new Scanner(System.in);
        printRequests = new View();
    }

    public Player setupPlayers(String playerNth, String pSymbol){
        String player;
        do {
            this.printRequests.playersTypeChoice(playerNth, pSymbol);
            player = this.setupPlayersScanner.nextLine();
        }while (!player.equals("1") && !player.equals("2"));
        //Need close scanner but don't know where
        return (player.equals("1")) ? new HumanPlayer(pSymbol) : new ArtificialPlayer(pSymbol);
    }

    public String askPlayerMove(String coordinate){
        this.printRequests.playerMoveChoice(coordinate);
        return moveScanner.nextLine();
        //Need close scanner but don't know where
    }

    public void getDisplayInputError(){
        this.printRequests.displayInputError();
    }

    public void getDisplayBoxIsFilled(){
        this.printRequests.boxIsFilled();
    }

    public String getPlayAgainChoice(){
        return newGameScanner.nextLine();
    }


}
