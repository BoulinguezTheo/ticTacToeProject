package src.controller;


import src.model.BoardGame;
import src.model.Cell;
import src.model.Player;
import src.vue.UserInteraction;
import src.vue.View;

import java.util.ArrayList;

public abstract class GameController {
    private int[] playersInput;
    protected final String resetSymbol = " ";
    protected View printer;
    protected BoardGame boardGame;
    public  Player player1;
    public Player player2;
    public UserInteraction interaction;
    GameState stateMachine;



    public GameController(){
        this.printer = new View();
        this.interaction = new UserInteraction();
        this.boardGame = new BoardGame();
    }
    public abstract void playGame();
    protected int[] getPlayersInput(){
        return this.playersInput;
    }
    protected void setPlayersInput(int[] pInput){
        this.playersInput = pInput;
    }
    protected void createPlayers(){
        this.player1 = this.interaction.setupPlayers("1st", "X");
        this.player2 = this.interaction.setupPlayers("2st", "O");
    }
    protected Cell[][] initCells(int pSizeHeight, int pSizeLength){
        Cell[][] cells = new Cell[pSizeLength][pSizeHeight];
        for (int i = 0; i < pSizeHeight; i++){
            for (int j = 0; j < pSizeLength; j++){
                cells[i][j] = new Cell();
            }
        }
        return cells;
    }
    protected void resetCells(String pSymbolReset, int pSizeHeight, int pSizeLength){
        for (int i = 0; i < pSizeHeight; i++){
            for (int j = 0; j < pSizeLength; j++){
                int[] resetCell = {j, i};
                this.boardGame.setOwner(resetCell, pSymbolReset);
            }
        }
    }
    protected abstract boolean checkIfGameWonColumnAndLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck);
    protected abstract boolean checkIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign);
    protected GameState treatPlayAgainChoice(){
        if (correctNewGameEntry().equalsIgnoreCase("y")){
            return GameState.RESETBOARD;
        }
        return GameState.EXIT;
    }
    private String correctNewGameEntry(){
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
