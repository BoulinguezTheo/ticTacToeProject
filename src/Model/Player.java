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
package src.Model;
import java.util.ArrayList;
import java.util.Scanner;

import src.Controller.UserInteraction;

public abstract class Player {
    private ArrayList<ArrayList<int[]>> lineArrays;
    private ArrayList<ArrayList<int[]>> columnArrays;
    private ArrayList<ArrayList<int[]>> diagXMinusOneArrays;
    private ArrayList<ArrayList<int[]>> diagXPlusOneArrays;


    public static int turn;
    String symbol;

    public Player (){
        this.turn = 0;
        lineArrays = new ArrayList<ArrayList<int[]>>();
        columnArrays = new ArrayList<ArrayList<int[]>>();
        diagXMinusOneArrays = new ArrayList<ArrayList<int[]>>();
        diagXPlusOneArrays = new ArrayList<ArrayList<int[]>>();
    }
    public Player(String pSymbol) {
        this.turn = 0;
        this.symbol = pSymbol;
        ArrayList<ArrayList<int[]>> lineArrays = new ArrayList<ArrayList<int[]>>();
        ArrayList<ArrayList<int[]>>columnArrays = new ArrayList<ArrayList<int[]>>();
        ArrayList<ArrayList<int[]>>diagXMinusOneArrays = new ArrayList<ArrayList<int[]>>();
        ArrayList<ArrayList<int[]>>diagXPlusOneArrays = new ArrayList<ArrayList<int[]>>();
    }
    public abstract int[] getMoveFromPlayer(UserInteraction interactor);
    public int[] askPlayerInput(UserInteraction interactor) {
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

    public boolean  validInputUser(String input, String typeInput, UserInteraction interactor ) {
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

    abstract public String getType();
}

