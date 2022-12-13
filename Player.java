import java.util.Scanner;

public class Player {
    private String representation;
    public static int turn = 0;
    String symbol;


    Player() {

    }

    public int getTurns(){
        return this.turn;
    }

    public String getRepresentation() {
        this.turn++;
        if (this.turn % 2 != 0) {
            symbol = "X";
            return symbol;
        } else {
            symbol = "O";
            return symbol;
        }
    }
    public String getSymbol(){
        return this.symbol;
    }

    public int[] getMoveFromPlayer() {
        int[] playerEntries;
        int column, line;
        //Check if box is occupied
        playerEntries = askPlayerInput();
        column = playerEntries[0];
        line = playerEntries[1];
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
            validColumn = validInputUser(column, "column");
        } while (!validColumn);

        //Check if line input is valid
        System.out.print("Enter line: ");
        do {
            line = playerInput.nextInt();
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
        } else {
            return true;
        }
    }
}

