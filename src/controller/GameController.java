package src.controller;


import src.model.BoardGame;
import src.model.Cell;
import src.model.GameInterface;
import src.model.Player;
import src.vue.ShowInterface;
import src.vue.UserInteraction;
import src.vue.ShowEn;
import src.vue.UserInteractionInterface;

import java.util.ArrayList;

public abstract class GameController implements GameControllerInterface{
    private int[] playersInput;
    protected final String resetSymbol = " ";
    protected ShowInterface printer;
    protected GameInterface boardGame;
    public  Player player1;
    public Player player2;
    public UserInteractionInterface interaction;
    protected GameState stateMachine;

    
    public GameController(){
        this.printer = new ShowEn();
        this.interaction = new UserInteraction();
        this.boardGame = new BoardGame();
    }
    public abstract void playGame();
    public int[] getPlayersInput(){
        return this.playersInput;
    }
    public void setPlayersInput(int[] pInput){
        this.playersInput = pInput;
    }
    public void createPlayers(){
        this.player1 = this.interaction.setupPlayers("1st", "X");
        this.player2 = this.interaction.setupPlayers("2st", "O");
    }
    public Cell[][] initCells(int pSizeHeight, int pSizeLength){
        Cell[][] cells = new Cell[pSizeLength][pSizeHeight];
        for (int i = 0; i < pSizeHeight; i++){
            for (int j = 0; j < pSizeLength; j++){
                cells[i][j] = new Cell();
            }
        }
        return cells;
    }
    public void resetCells(String pSymbolReset, int pSizeHeight, int pSizeLength){
        for (int i = 0; i < pSizeHeight; i++){
            for (int j = 0; j < pSizeLength; j++){
                int[] resetCell = {j, i};
                this.boardGame.setOwner(resetCell, pSymbolReset);
            }
        }
    }
    public abstract boolean checkIfGameWonColumnAndLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck);
    public abstract boolean checkIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign);
    public GameState treatPlayAgainChoice(){
        if (correctNewGameEntry().equalsIgnoreCase("y")){
            return GameState.RESETBOARD;
        }
        return GameState.EXIT;
    }
    public String correctNewGameEntry(){
        boolean correctEntry = false;
        String newGameChoice;
        do {
            this.printer.displayAskNewGame();
            newGameChoice = this.interaction.getPlayAgainChoice();
            if (newGameChoice.equalsIgnoreCase("Y") || newGameChoice.equalsIgnoreCase("N")) {
                correctEntry = true;
            }
        } while (!correctEntry);
        return newGameChoice;
    }
}
