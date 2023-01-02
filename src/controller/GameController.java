package src.controller;


import src.model.BoardGame;
import src.model.Cell;
import src.model.Player;
import src.vue.UserInteraction;
import src.vue.View;

import java.util.ArrayList;

public abstract class GameController {
    private int[] playersInput;
    View printer;
    BoardGame boardGame;
    public  Player player1;
    public Player player2;
    public UserInteraction interaction;



    public GameController(){
        this.printer = new View();
        this.interaction = new UserInteraction();
        this.boardGame = new BoardGame();
        createPlayers();
    }
    protected abstract void playGame();
    public void getPlayGame(){
        playGame();
    }
    protected int[] getPlayersInput(){
        return this.playersInput;
    }
    protected void setPlayersInput(int[] pInput){
        this.playersInput = pInput;
    }
    private void createPlayers(){
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
    protected abstract boolean checkIfGameWonColumnAndLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck);
    protected abstract boolean checkIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign);



}
