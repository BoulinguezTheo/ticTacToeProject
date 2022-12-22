package src;

// import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe extends BoardGame{
    private final int winCondition = 3;
    private int sizeHeight;
    private int sizeLength;
    private int[] playersInput;
    // private boolean again;
    // UserInteraction interaction;
    View printer;
    Player activePlayer;


    public TicTacToe() {
        //Initiate variables
        this.sizeHeight = 3;
        this.sizeLength = 3;
        // Initiate Objects
        super.boardGame = initCells();
        this.printer = new View();
    }

    protected int getSizeLength(){
        return this.sizeLength;
    }

    protected int getSizeHeight(){
        return this.sizeHeight;
    }

    // @Override
    // protected void play(){
    //     do {
    //         playGame();
    //         again = this.interaction.playAgain();
    //     }while(again);
    // }

    @Override
    protected void playGame(){
        do {
            super.turns++;
            activePlayer = (activePlayer == player1) ? player2 : player1;
            this.printer.display(this.sizeLength, this.sizeHeight, super.boardGame);
            getValidMove();
            setOwner(playersInput);
        } while(!isWinner() && super.turns != 9);


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

    public void getValidMove(){
        this.printer.displayPlayerTurn(activePlayer.getSymbol());
        do {
            playersInput = this.activePlayer.getMoveFromPlayer();
        } while (isBoxFilled(playersInput));
    }
    public boolean isBoxFilled(int[] input){
        int inputColumn = input[1];
        int inputLine = input[0];
        if(super.boardGame[inputColumn][inputLine].representation == " "){
            return false;
        } else {
            if (activePlayer.getType().equals("Human")){
                this.printer.boxIsFilled();
            }
            return true;
        }
    }
    public void setOwner(int[] input){
        super.boardGame[input[1]][input[0]].representation = activePlayer.getSymbol();

    }
    public boolean isWinner(){
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
        boolean entered = false;
        for(ArrayList<int[]> array : arrayToCheck){
            int[] getTuple = array.get(0);
            int coordinateToFit = getTuple[coordinateToCheck];
            if(playersInput[coordinateToCheck] == coordinateToFit){
                array.add(playersInput);
                entered = true;
            }
            if (array.size() == this.winCondition){
                this.printer.display(this.sizeLength, this.sizeHeight, super.boardGame);
                this.printer.displayerWinner(this.activePlayer.getSymbol());
                return true;
            }
        }
        if (!entered){
            createNewArrayOfCoordinates(arrayToCheck);
        }

        return false;
    }
    private boolean checkIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign){
        int x = playersInput[0];
        int y = playersInput[1];
        boolean entered = false;
        for(ArrayList<int[]> array : arrayToCheck){
            int[] getTuple = array.get(0);
            int coordinateToFitX = (x - getTuple[0]);
            int coordinateToFitY = sign * (y - getTuple[1]);
            if(coordinateToFitX == coordinateToFitY){
                array.add(playersInput);
            }
            if (array.size() == this.winCondition){
                this.printer.display(this.sizeLength, this.sizeHeight, super.boardGame);
                this.printer.displayerWinner(this.activePlayer.getSymbol());
                return true;
            }
        }
        if (!entered){
            createNewArrayOfCoordinates(arrayToCheck);
        }

        return false;
    }
    private void createNewArrayOfCoordinates(ArrayList<ArrayList<int[]>> arrayToCheck){
        ArrayList<int[]> array = new ArrayList<int[]>();
        array.add(playersInput);
        arrayToCheck.add(array);
    }
}
