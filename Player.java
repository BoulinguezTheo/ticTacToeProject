import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    public static int turn;
    String symbol;

    public Player (){
        this.turn = 0;




    }
    public Player(String pSymbol) {
        this.turn = 0;
        this.symbol = pSymbol;
    }
    public int[] getMoveFromPlayer() {
        int[] playerEntries;
        //Check if box is occupied
        playerEntries = askPlayerInput();
        return playerEntries;

    }
    public int[] askPlayerInput() {
        boolean validColumn, validLine;
        Scanner playerInput = new Scanner(System.in);
        int column, line;
        //Check if column input is valid
        System.out.print("Enter column: ");
        do {
            column = playerInput.nextInt();
//            System.out.println(column.getClass().getName());
            validColumn = validInputUser(column, "Column");
        } while (!validColumn);

        //Check if line input is valid
        System.out.print("Enter line: ");
        do {
            line = playerInput.nextInt();
            validLine = validInputUser(line, "line");
        } while (!validLine);

//        playerInput.close(); //fait bugger
        return new int[]{line, column};
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
//    public ArrayList<ArrayList<int[]>> getLineArrays(){
//        return lineArrays;
//    }
//    public ArrayList<ArrayList<int[]>> getColumnArrays(){
//        return columnArrays;
//    }
//    public ArrayList<ArrayList<int[]>> diagXMinusOneArrays(){
//        return diagXMinusOneArrays;
//    }
//    public ArrayList<ArrayList<int[]>> diagXPlusOneArrays(){
//        return diagXPlusOneArrays;
//    }
}

