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
            super.printer.displayBoard(this.sizeLength, this.sizeHeight, super.boardGame);
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
        super.printer.displayPlayerTurn(activePlayer.getSymbol());
        do {
            playersInput = this.activePlayer.getMoveFromPlayer(interaction);
        } while (isBoxFilled(playersInput));
    }
    public boolean  isBoxFilled(int[] input){
        int inputColumn = input[1];
        int inputLine = input[0];
        if(super.boardGame[inputColumn][inputLine].representation == " "){
            return false;
        } else {
            if (activePlayer.getType().equals("Human")){
                this.interaction.getDisplayBoxIsFilled(); 
            }
            return true;
        }
    }
    public void setOwner(int[] input){
        super.boardGame[input[1]][input[0]].representation = activePlayer.getSymbol();
    }
    public boolean isWinner(){
        return super.processInputColumnLines(activePlayer.getLineArrays(), 0, playersInput)
                || super.processInputColumnLines(activePlayer.getColumnArrays(), 1, playersInput)
                || super.processInputDiags(activePlayer.diagXMinusOneArrays(), -1, playersInput)
                || super.processInputDiags(activePlayer.diagXPlusOneArrays(), 1, playersInput);
    }
   
    // private boolean checkIfGameWonColumnAndLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck){
    //     boolean entered = false;
    //     for(ArrayList<int[]> array : arrayToCheck){
    //         int[] getTuple = array.get(0);
    //         int coordinateToFit = getTuple[coordinateToCheck];
    //         if(playersInput[coordinateToCheck] == coordinateToFit){
    //             array.add(playersInput);
    //             entered = true;
    //         }
    //         if (array.size() == this.winCondition){
    //             this.printer.display(this.sizeLength, this.sizeHeight, super.boardGame);
    //             this.printer.displayerWinner(this.activePlayer.getSymbol());
    //             return true;
    //         }
    //     }
    //     if (!entered){
    //         createNewArrayOfCoordinates(arrayToCheck);
    //     }

    //     return false;
    // }
    // private boolean checkIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign){
    //     int x = playersInput[0];
    //     int y = playersInput[1];
    //     boolean entered = false;
    //     for(ArrayList<int[]> array : arrayToCheck){
    //         int[] getTuple = array.get(0);
    //         int coordinateToFitX = (x - getTuple[0]);
    //         int coordinateToFitY = sign * (y - getTuple[1]);
    //         if(coordinateToFitX == coordinateToFitY){
    //             array.add(playersInput);
    //         }
    //         if (array.size() == this.winCondition){
    //             this.printer.display(this.sizeLength, this.sizeHeight, super.boardGame);
    //             this.printer.displayerWinner(this.activePlayer.getSymbol());
    //             return true;
    //         }
    //     }
    //     if (!entered){
    //         createNewArrayOfCoordinates(arrayToCheck);
    //     }

    //     return false;
    // }

}
