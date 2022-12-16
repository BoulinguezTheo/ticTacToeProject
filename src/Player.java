
package src;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Player {
    private ArrayList<ArrayList<int[]>> lineArrays;
    private ArrayList<ArrayList<int[]>> columnArrays;
    private ArrayList<ArrayList<int[]>> diagXMinusOneArrays;
    private ArrayList<ArrayList<int[]>> diagXPlusOneArrays;

    UserInteraction interaction;
    public static int turn;
    String symbol;

    public Player (){
        this.turn = 0;
        lineArrays = new ArrayList<ArrayList<int[]>>();
        columnArrays = new ArrayList<ArrayList<int[]>>();
        diagXMinusOneArrays = new ArrayList<ArrayList<int[]>>();
        diagXPlusOneArrays = new ArrayList<ArrayList<int[]>>();
        interaction = new UserInteraction();
    }
    public Player(String pSymbol) {
        this.turn = 0;
        this.symbol = pSymbol;
        ArrayList<ArrayList<int[]>> lineArrays = new ArrayList<ArrayList<int[]>>();
        ArrayList<ArrayList<int[]>>columnArrays = new ArrayList<ArrayList<int[]>>();
        ArrayList<ArrayList<int[]>>diagXMinusOneArrays = new ArrayList<ArrayList<int[]>>();
        ArrayList<ArrayList<int[]>>diagXPlusOneArrays = new ArrayList<ArrayList<int[]>>();
        interaction = new UserInteraction();
    }
    protected abstract int[] getMoveFromPlayer();
    public int[] askPlayerInput() {
        boolean validColumn, validLine;
        int column, line;

        //Check if column input is valid
        do {
            column = interaction.askPlayerMove("column");
            validColumn = validInputUser(column, "column");
        } while (!validColumn);

        //Check if line input is valid
        do {
            line = interaction.askPlayerMove("line");
            validLine = validInputUser(line, "line");
        } while (!validLine);
        return new int[]{column, line};
    }

    public boolean validInputUser(int input, String typeInput) {
        //Check if the input is valid
        if (input != 0 && input != 1 && input != 2) {
            System.out.println("Error: please try again");
            System.out.print("Enter " + typeInput + ": ");
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
    public ArrayList<ArrayList<int[]>> diagXMinusOneArrays(){
        return diagXMinusOneArrays;
    }
    public ArrayList<ArrayList<int[]>> diagXPlusOneArrays(){
        return diagXPlusOneArrays;
    }

    abstract protected String getType();
}

