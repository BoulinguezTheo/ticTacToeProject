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
        this.lineArrays = new ArrayList<>();
        this.columnArrays = new ArrayList<>();
        this.diagXMinusOneArrays = new ArrayList<>();
        this.diagXPlusOneArrays = new ArrayList<>();
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

