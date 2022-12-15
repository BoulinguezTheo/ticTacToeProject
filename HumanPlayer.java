import java.util.Scanner;

public class HumanPlayer extends Player{
    private String symbol;

    public HumanPlayer(String pSymbol){
        this.symbol = pSymbol;
    }
    @Override
    protected String getType(){
        return "Human";
    }

    @Override
    public int[] getMoveFromPlayer() {
        //Check if box is occupied
        int [] playerEntries = askPlayerInput();
        return playerEntries;
    }

    @Override
    public int[] askPlayerInput() {
        boolean validColumn, validLine;
        Scanner playerInput = new Scanner(System.in);
        int column, line;
        //Check if column input is valid
        System.out.print("Enter column: ");
        do {
            column = playerInput.nextInt();
            validColumn = super.validInputUser(column, "Column");
        } while (!validColumn);

        //Check if line input is valid
        System.out.print("Enter line: ");
        do {
            line = playerInput.nextInt();
            validLine = super.validInputUser(line, "line");
        } while (!validLine);

        //playerInput.close(); //fait bugger
        return new int[]{column, line};
    }

    public String getSymbol(){
        return this.symbol;
    }

}
