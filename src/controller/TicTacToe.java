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

import java.util.ArrayList;
import src.model.Player;
public class TicTacToe extends GameController  {
    private final int winCondition = 3;
    private final int endGameByTurns = 9;
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
        super.stateMachine = GameState.INITGAME;
    }

    @Override
    public void play(){

        while(stateMachine != GameState.EXIT){
            switch (stateMachine){
                case INITGAME:
                    stateMachine = super.initGameFunctions();
                    break;
                case PLAYGAME:
                    stateMachine = playGame();
                    break;
                case ENDGAME:
                    stateMachine = GameState.PLAYAGAIN;
                    break;
                case PLAYAGAIN:
                    stateMachine = super.treatPlayAgainChoice();
                    break;
                case RESETBOARD:
                    stateMachine = super.resetBoard(this.sizeHeight, this.sizeLength);
                    break;
                case EXIT:
                    break;
            }
        }
        printer.displayExit();
    }
    @Override
    public void getValidMove(){
        printer.displayPlayerTurn(this.activePlayer.getSymbol());
        do {
            setPlayersInput(this.activePlayer.getMoveFromPlayer(interaction));
        } while (isBoxFilled(super.getPlayersInput()));
    }

    public boolean  isBoxFilled(int[] input){
        int inputColumn = input[1];
        int inputLine = input[0];
        if(boardGame.getBoardCell()[inputColumn][inputLine].representation == " "){
            return false;
        } else {
            if (this.activePlayer.getType().equals("Human")){
                interaction.getDisplayBoxIsFilled();
            }
            return true;
        }
    }
    protected GameState playGame(){
        this.activePlayer = super.addTurnSetActivePlayerDisplayBoard(this.activePlayer, this.sizeHeight, this.sizeLength);
        return super.moveValidAndSetStateMachine(this.activePlayer);
    }
    /**
     *  Fonction permettant de déterminer si la partie est gagnée
     */
    public GameState isGameOver(){
        if(isWinner()){
            printer.displayBoard(this.sizeLength, this.sizeHeight, boardGame.getBoardCell());
            printer.displayWinner(activePlayer.getSymbol());
            return GameState.ENDGAME;
        } else if (super.boardGame.getTurns() == endGameByTurns){
            printer.displayBoard(this.sizeLength, this.sizeHeight, boardGame.getBoardCell());
            printer.displayDraw();
            return GameState.ENDGAME;
        }
        return GameState.PLAYGAME;
    }

    private boolean isWinner(){
        return boardGame.processInputColumnLines(activePlayer.getLineArrays(), 0, super.getPlayersInput(), this)
                || boardGame.processInputColumnLines(activePlayer.getColumnArrays(), 1, super.getPlayersInput(), this)
                || boardGame.processInputDiags(activePlayer.getDiagXMinusOneArrays(), -1, super.getPlayersInput(), this)
                || boardGame.processInputDiags(activePlayer.getDiagXPlusOneArrays(), 1, super.getPlayersInput(), this);
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
                return true;
            }
        }
        if (!entered){
            boardGame.createNewArrayOfCoordinates(arrayToCheck, super.getPlayersInput());
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
                return true;
            }
        }
        if (!entered){
            boardGame.createNewArrayOfCoordinates(arrayToCheck, playersInput);
        }
        return false;
    }

}
