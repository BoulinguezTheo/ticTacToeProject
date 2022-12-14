//import java.util.Scanner;
//
//public class HumanPlayer extends Player{
//    private String symbol;
//
//    public HumanPlayer(String pSymbol){
//        this.symbol = pSymbol;
//    }
//    @Override
//    public int[] getMoveFromPlayer() {
//        int[] playerEntries;
//        //Check if box is occupied
//        playerEntries = askPlayerInput();
//        return playerEntries;
//    }
//    @Override
//    public int[] askPlayerInput() {
//        boolean validColumn, validLine;
//        Scanner playerInput = new Scanner(System.in);
//        int column, line;
//        //Check if column input is valid
//        System.out.print("Enter column: ");
//        do {
//            column = playerInput.nextInt();
////            System.out.println(column.getClass().getName());
//            validColumn = super.validInputUser(column, "Column");
//        } while (!validColumn);
//
//        //Check if line input is valid
//        System.out.print("Enter line: ");
//        do {
//            line = playerInput.nextInt();
//            validLine = super.validInputUser(line, "line");
//        } while (!validLine);
//
////        playerInput.close(); //fait bugger
//        return new int[]{column, line};
//    }
//
//}
