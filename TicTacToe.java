import java.util.ArrayList;
import java.util.Arrays;

public class TicTacToe {
    private final int winCondition = 3;
    private int sizeHeight;
    private int sizeLength;
    private int[] playersInput;

    public int turn;
    private Cell[][] cellsBoard;
    Cell cell;
    Player player1;
    Player player2;
    Player activePlayer;


    public TicTacToe() {
        //Initiate variables
        this.sizeHeight = 3;
        this.sizeLength = 3;
        this.turn = 0;
        // Initiate Objects
        this.cell = new Cell();
        this.cellsBoard = initCells();
        player1 = new Player("X");
        player2 = new Player("O");
        activePlayer = player1;
    }

    protected void play(){
        do {
            this.turn++;
            display();
            getValidMove();
            setOwner(playersInput);
            activePlayer = (activePlayer == player1) ? player2 : player1;
        } while(!isWinner() && this.turn != 9);
        display();
    }

    private Cell[][] initCells(){
        Cell[][] cells = new Cell[this.sizeLength][this.sizeHeight];
        for (int i = 0; i < this.sizeHeight; i++){
            for (int j = 0; j < this.sizeLength; j++){
                cells[i][j] = new Cell();
            }
        }
        return cells;
    }

    public void display() {
        System.out.println("-------------");
        for (int i = 0; i < this.sizeLength; i++) {
            for (int j = 0; j < this.sizeHeight; j++) {
                System.out.print(this.cellsBoard[i][j].getCell());
            }
            System.out.println("|");
            System.out.println("-------------");
        }
    }

    public void getValidMove(){
        do {
            playersInput = this.activePlayer.getMoveFromPlayer();
//            System.out.println(Arrays.toString(playersInput));
        } while (isBoxFilled(playersInput));
    }
    public boolean isBoxFilled(int[] input){
        int inputColumn = input[0];
        int inputLine = input[1];
        if(cellsBoard[inputColumn][inputLine].representation == " "){
            return false;
        } else {
            System.out.println("Box already filled, please try again");
            return true;
        }
    }
    public void setOwner(int[] input){
        cellsBoard[input[0]][input[1]].representation = activePlayer.symbol;
    }
    public boolean isWinner(){
        String symbolPlayed = activePlayer.symbol;
        return treatInputColumnLines(activePlayer.getLineArrays(), 0)
                || treatInputColumnLines(activePlayer.getColumnArrays(), 1)
                || treatInputDiags(activePlayer.diagXMinusOneArrays(), -1)
                || treatInputDiags(activePlayer.diagXPlusOneArrays(), 1);
    }
    private boolean treatInputColumnLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck){
        if (arrayToCheck.size() == 0){
            createNewArrayOfCoordinates(arrayToCheck);
            return false;
        } else {
            return checkIfGameWonColumnAndLines(arrayToCheck,coordinateToCheck);
        }
    }
    private boolean treatInputDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign){
        if (arrayToCheck.size() == 0){
            createNewArrayOfCoordinates(arrayToCheck);
            return false;
        } else {
            return checkIfGameWonDiags(arrayToCheck, sign);
        }
    }
    private boolean checkIfGameWonColumnAndLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck){
        for(ArrayList<int[]> array : arrayToCheck){
            int[] getTuple = array.get(0);
            int coordinateToFit = getTuple[coordinateToCheck];
            if(playersInput[coordinateToCheck] == coordinateToFit){
                array.add(playersInput);
            }
            System.out.println(array.size());
            if (array.size() == this.winCondition){
                return true;
            }
        }
        return false;
    }
    private boolean checkIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign){
        System.out.println("test diags");
        int x = playersInput[0];
        int y = playersInput[1];
        for(ArrayList<int[]> array : arrayToCheck){
            int[] getTuple = array.get(0);
            int coordinateToFitX = (x - getTuple[0]);
            int coordinateToFitY = sign * (y - getTuple[0]);
            if(coordinateToFitX == coordinateToFitY){
                array.add(playersInput);
            }
            if (array.size() == this.winCondition){
                return true;
            }
        }
        return false;
    }
    private void createNewArrayOfCoordinates(ArrayList<ArrayList<int[]>> arrayToCheck){
        ArrayList<int[]> array = new ArrayList<int[]>();
        array.add(playersInput);
        arrayToCheck.add(array);
    }
}
