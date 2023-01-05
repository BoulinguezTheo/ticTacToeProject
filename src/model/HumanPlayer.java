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
import src.controller.GameControllerInterface;
import src.vue.ShowInterface;
import src.vue.UserInteractionInterface;

public class HumanPlayer extends Player{
    final protected String TYPE = "Human";

    public int[] playMove() {
        return new int[0];
    }
    public HumanPlayer(String pSymbol){
        super.symbol = pSymbol;
    }
    @Override
    public String getType(){
        return this.TYPE;
    }

    public String getSymbol(){
        return this.symbol;
    }

}
