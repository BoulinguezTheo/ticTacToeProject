package src.controller;

import src.model.Cell;

import java.util.ArrayList;

public interface GameControllerInterface {

    void playGame();
    int[] getPlayersInput();
    void setPlayersInput(int[] pInput);
    void createPlayers();
    Cell[][] initCells(int pSizeHeight, int pSizeLength);
    void resetCells(String pSymbolReset, int pSizeHeight, int pSizeLength);
    boolean checkIfGameWonColumnAndLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck);
    boolean checkIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign);
    GameState treatPlayAgainChoice();
    String correctNewGameEntry();

}
