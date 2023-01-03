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
import src.model.HumanPlayer;
import src.model.Player;

public class UserInteraction implements UserInteractionInterface {
    Scanner setupPlayersScanner;
    Scanner moveScanner;
    Scanner newGameScanner;
    ShowInterface printRequests;

    public UserInteraction(){
        setupPlayersScanner = new Scanner(System.in);
        moveScanner = new Scanner(System.in);
        newGameScanner = new Scanner(System.in);
        printRequests = new ShowEn();
    }
    @Override
    public Player setupPlayers(String playerNth, String pSymbol){
        String player;
        do {
            this.printRequests.playersTypeChoice(playerNth, pSymbol);
            player = this.setupPlayersScanner.nextLine();
        }while (!player.equals("1") && !player.equals("2"));
        //Need close scanner but don't know where
        return (player.equals("1")) ? new HumanPlayer(pSymbol) : new ArtificialPlayer(pSymbol);
    }
    @Override
    public String askPlayerMove(String coordinate){
        this.printRequests.playerMoveChoice(coordinate);
        return moveScanner.nextLine();
        //Need close scanner but don't know where
    }
    @Override
    public void getDisplayInputError(){
        this.printRequests.displayInputError();
    }
    @Override
    public void getDisplayBoxIsFilled(){
        this.printRequests.boxIsFilled();
    }
    @Override
    public String getPlayAgainChoice(){
        return newGameScanner.nextLine();
    }


}
