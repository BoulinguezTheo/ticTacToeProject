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
import src.vue.ShowInterface;
import src.vue.UserInteractionInterface;

public class HumanPlayer extends Player{
    final protected String TYPE = "Human";

    public HumanPlayer(String pSymbol){
        super.symbol = pSymbol;
    }
    @Override
    public String getType(){
        return this.TYPE;
    }

    @Override
    public int[] getMoveFromPlayer(UserInteractionInterface interactor, ShowInterface pPrinter) {
        //Check if box is occupied
        int [] playerEntries = askPlayerInput(interactor, pPrinter);
        return playerEntries;
    }

    public String getSymbol(){
        return this.symbol;
    }

}
