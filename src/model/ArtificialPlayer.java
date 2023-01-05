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

import src.vue.UserInteractionInterface;

public class ArtificialPlayer extends Player {
    private String symbol;
    final protected String TYPE = "Bot";

    public ArtificialPlayer(String pSymbol){
        this.symbol = pSymbol;
    }
    @Override
    public String getTYPE(){
        return this.TYPE;
    }

    @Override
    public int[] getMoveFromPlayer(UserInteractionInterface interactor) {
        //Check if box is occupied
        int [] playerEntries = generateRandomInputs();
        return playerEntries;
    }

    private int[] generateRandomInputs(){
        Random randomInt = new Random();
        int column = randomInt.nextInt(0, 3);
        int line = randomInt.nextInt(0, 3);
        return new int[] {column, line};
    }

    public String getSymbol(){
        return this.symbol;
    }

}
