/*
 * Nom de classe : Player
 *
 * Description   : Classe abstraite regroupant les fonctions communes aux différents types de joueurs possibles.
 *
 * Version       : 1.0
 *
 * Date          : 02/01/2023
 *
 * Copyright     : moi
 */
package src.model;
import java.util.ArrayList;

import src.vue.ShowInterface;
import src.vue.UserInteraction;
import src.vue.UserInteractionInterface;

public abstract class Player {
    private ArrayList<ArrayList<int[]>> lineArrays;
    private ArrayList<ArrayList<int[]>> columnArrays;
    private ArrayList<ArrayList<int[]>> diagXMinusOneArrays;
    private ArrayList<ArrayList<int[]>> diagXPlusOneArrays;
    String symbol;

    public Player (){
        lineArrays = new ArrayList<>();
        columnArrays = new ArrayList<>();
        diagXMinusOneArrays = new ArrayList<>();
        diagXPlusOneArrays = new ArrayList<>();
    }
    public Player(String pSymbol) {
        this.symbol = pSymbol;
        ArrayList<ArrayList<int[]>> lineArrays = new ArrayList<>();
        ArrayList<ArrayList<int[]>> columnArrays = new ArrayList<>();
        ArrayList<ArrayList<int[]>> diagXMinusOneArrays = new ArrayList<>();
        ArrayList<ArrayList<int[]>> diagXPlusOneArrays = new ArrayList<>();
    }
    public abstract int[] getMoveFromPlayer(UserInteractionInterface interactor);
    public int[] askPlayerInput(UserInteractionInterface interactor) {
        boolean validColumn, validLine;
        String column, line;

        //Check if column input is valid
        do {
            column = interactor.askPlayerMove("column");
            validColumn = validInputUser(column, "column", interactor);
        } while (!validColumn);

        //Check if line input is valid
        do {
            line = interactor.askPlayerMove("line");
            validLine = validInputUser(line, "line", interactor);
        } while (!validLine);

        return new int[]{Integer.parseInt(column), Integer.parseInt(line)};
    }

    public boolean  validInputUser(String input, String typeInput, UserInteractionInterface interactor ) {
        //Check if the input is valid
        if (!input.equals("0") && !input.equals("1") && !input.equals("2")) {
            interactor.getDisplayInputError(); 
            return false;
        }
        return true; 
    }
    public String getSymbol(){
        return this.symbol;
    }
    public ArrayList<ArrayList<int[]>> getLineArrays(){
        return lineArrays;
    }
    public ArrayList<ArrayList<int[]>> getColumnArrays(){
        return columnArrays;
    }
    public ArrayList<ArrayList<int[]>> getDiagXMinusOneArrays(){
        return diagXMinusOneArrays;
    }
    public ArrayList<ArrayList<int[]>> getDiagXPlusOneArrays(){
        return diagXPlusOneArrays;
    }

    abstract public String getTYPE();
}

