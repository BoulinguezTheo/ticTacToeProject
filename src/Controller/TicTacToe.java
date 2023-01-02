/*
 * Nom de classe : TicTacToe
 *
 * Description   : Classe regroupant les fonctions spécifiques au bon fonctionnement et respectant les règles d'un jeu
 *                 de tictactoe.
 *
 * Version       : 1.0
 *
 * Date          : 02/01/2023
 *
 * Copyright     : moi
 */
package src.Controller;
// import org.w3c.dom.ls.LSOutput;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import src.Model.BoardGame;
import src.Model.Cell;
import src.Model.Player;

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
    }

    @Override
    protected void playGame(){
        do {
            super.turns++;
            activePlayer = (activePlayer == player1) ? player2 : player1;
            super.printer.displayBoard(this.sizeLength, this.sizeHeight, super.boardGame);
            getValidMove(); 
            super.setOwner(playersInput, activePlayer);
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

    /**
     *  Fonction permettant de déterminer si la partie est gagnée
     */
    public boolean isWinner(){
        return super.processInputColumnLines(activePlayer.getLineArrays(), 0, playersInput)
                || super.processInputColumnLines(activePlayer.getColumnArrays(), 1, playersInput)
                || super.processInputDiags(activePlayer.getDiagXMinusOneArrays(), -1, playersInput)
                || super.processInputDiags(activePlayer.getDiagXPlusOneArrays(), 1, playersInput);
    }
    /**
     *  Permet de déterminé si une ligne ou une colonne est gagnante
     */
    @Override
    protected boolean checkIfGameWonColumnAndLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck){
        boolean entered = false;
        for(ArrayList<int[]> array : arrayToCheck){
            int[] getTuple = array.get(0);
            int coordinateToFit = getTuple[coordinateToCheck];
            if(playersInput[coordinateToCheck] == coordinateToFit){
                array.add(playersInput);
                entered = true;
            }
            if (array.size() == this.winCondition){
                this.interaction.getDisplayBoard(this.sizeLength, this.sizeHeight, super.boardGame);
                this.interaction.getDisplayWinner(this.activePlayer.getSymbol()); 
                return true;
            }
        }
        if (!entered){
            super.createNewArrayOfCoordinates(arrayToCheck, playersInput);
        }

        return false;
    }
    /**
     *  Fonction permettant de déterminer si une digonale est gagnante.
     */
    protected boolean checkIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign){
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
                this.interaction.getDisplayBoard(this.sizeLength, this.sizeHeight, super.boardGame);
                this.interaction.getDisplayWinner(this.activePlayer.getSymbol());
                return true;
            }
        }
        if (!entered){
            createNewArrayOfCoordinates(arrayToCheck, playersInput);
        }
        return false;
    }

}
