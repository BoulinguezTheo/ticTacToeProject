/*
 * Nom de classe : HumanPlayer
 *
 * Description   : Classe regroupant les fonctions spécifiques au bon fonctionnement d'un joueur humain.
 *
 * Version       : 1.0
 *
 * Date          : 02/01/2023
 *
 * Copyright     : moi
 */
package src.model;
import src.vue.UserInteractionInterface;

public class HumanPlayer extends Player{
    private String symbol;
    final protected String type = "Human";

    public HumanPlayer(String pSymbol){
        this.symbol = pSymbol;
    }
    @Override
    public String getTYPE(){
        return this.type ;
    }

    @Override
    public int[] getMoveFromPlayer(UserInteractionInterface interactor) {
        //Check if box is occupied
        int [] playerEntries = askPlayerInput(interactor);
        return playerEntries;
    }

    public String getSymbol(){
        return this.symbol;
    }

}
