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

import src.controller.GameControllerInterface;
import src.model.ArtificialPlayer;
import src.model.HumanPlayer;
import src.model.Player;

public class UserInteraction implements UserInteractionInterface {
    Scanner setupPlayersScanner;
    Scanner moveScanner;
    Scanner newGameScanner;
    Scanner gameChoiceScanner;
    ShowInterface printRequests;

    public UserInteraction(){
        setupPlayersScanner = new Scanner(System.in);
        moveScanner = new Scanner(System.in);
        newGameScanner = new Scanner(System.in);
        gameChoiceScanner = new Scanner(System.in);
    }
    @Override
    public String getGameChoice(){
        return this.gameChoiceScanner.nextLine();
    }
    @Override
    public String askPlayerType(String playerNth, String pSymbol, GameControllerInterface pController){
        String player;
        do {
            pController.getPlayerTypeChoice(playerNth, pSymbol);
            player = this.setupPlayersScanner.nextLine();
        }while (!player.equals("1") && !player.equals("2"));
        //Need close scanner but don't know where
        return player;
    }
    @Override
    public String askPlayerMove(String coordinate){
        return moveScanner.nextLine();
        //Need close scanner but don't know where
    }
    @Override
    public String getPlayAgainChoice(){
        return newGameScanner.nextLine();
    }



}
