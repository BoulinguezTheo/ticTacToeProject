/*
 * Nom de classe : ArtificiaPlayer
 *
 * Description   : Classe regroupant les fonctions spécifiques au bon fonctionnement d'un joueur artificiel.
 *
 * Version       : 1.0
 *
 * Date          : 02/01/2023
 *
 * Copyright     : moi
 */
package src.model;

import java.util.Random;

import src.vue.ShowInterface;
import src.vue.UserInteractionInterface;

public class ArtificialPlayer extends Player {

    final protected String TYPE = "Bot";

    public ArtificialPlayer(String pSymbol){
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
