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
package src.controller;
// import org.w3c.dom.ls.LSOutput;


import java.util.ArrayList;
import src.vue.UserInteraction;
import src.model.Player;

public class TicTacToe extends GameController  {
    private final int winCondition = 3;
    private int sizeHeight;
    private int sizeLength;
    // private boolean again;
    Player activePlayer;


    public TicTacToe() {
        //Initiate variables
        this.sizeHeight = 3;
        this.sizeLength = 3;
        // Initiate Objects
        super.boardGame.setBoardCell(super.initCells(this.sizeHeight, this.sizeLength));
    }

    @Override
    protected void playGame(){
        do {
            super.boardGame.turns++;
            this.activePlayer = (this.activePlayer == super.player1) ? super.player2 : super.player1;
            super.printer.displayBoard(this.sizeLength, this.sizeHeight, super.boardGame.getBoardCell());
            getValidMove(); 
            super.boardGame.setOwner(super.getPlayersInput(), activePlayer);
        } while(!isWinner() && super.boardGame.turns != 9);
    }
 
    public void getValidMove(){
        super.printer.displayPlayerTurn(this.activePlayer.getSymbol());
        do {
            setPlayersInput(this.activePlayer.getMoveFromPlayer(interaction));
        } while (isBoxFilled(super.getPlayersInput()));
    }

    public boolean  isBoxFilled(int[] input){
        int inputColumn = input[1];
        int inputLine = input[0];
        if(super.boardGame.getBoardCell()[inputColumn][inputLine].representation == " "){
            return false;
        } else {
            if (activePlayer.getType().equals("Human")){
                super.interaction.getDisplayBoxIsFilled();
            }
            return true;
        }
    }

    /**
     *  Fonction permettant de déterminer si la partie est gagnée
     */
    public boolean isWinner(){
        return super.boardGame.processInputColumnLines(activePlayer.getLineArrays(), 0, super.getPlayersInput(), this)
                || super.boardGame.processInputColumnLines(activePlayer.getColumnArrays(), 1, super.getPlayersInput(), this)
                || super.boardGame.processInputDiags(activePlayer.getDiagXMinusOneArrays(), -1, super.getPlayersInput(), this)
                || super.boardGame.processInputDiags(activePlayer.getDiagXPlusOneArrays(), 1, super.getPlayersInput(), this);
    }
    /**
     *  Permet de déterminé si une ligne ou une colonne est gagnante
     */
    @Override
    public boolean checkIfGameWonColumnAndLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck){
        boolean entered = false;
        for(ArrayList<int[]> array : arrayToCheck){
            int[] getTuple = array.get(0);
            int coordinateToFit = getTuple[coordinateToCheck];
            int[] playersInput = super.getPlayersInput();
            if(playersInput[coordinateToCheck] == coordinateToFit){
                array.add(getPlayersInput());
                entered = true;
            }
            if (array.size() == this.winCondition){
                super.interaction.getDisplayBoard(this.sizeLength, this.sizeHeight, super.boardGame.getBoardCell());
                super.interaction.getDisplayWinner(this.activePlayer.getSymbol());
                return true;
            }
        }
        if (!entered){
            super.boardGame.createNewArrayOfCoordinates(arrayToCheck, super.getPlayersInput());
        }

        return false;
    }
    /**
     *  Fonction permettant de déterminer si une digonale est gagnante.
     */
    public boolean checkIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign){
        int[] playersInput = super.getPlayersInput();
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
                this.interaction.getDisplayBoard(this.sizeLength, this.sizeHeight, super.boardGame.getBoardCell());
                this.interaction.getDisplayWinner(this.activePlayer.getSymbol());
                return true;
            }
        }
        if (!entered){
            super.boardGame.createNewArrayOfCoordinates(arrayToCheck, playersInput);
        }
        return false;
    }

}
