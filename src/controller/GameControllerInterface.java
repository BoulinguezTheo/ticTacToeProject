package src.controller;

import src.model.Cell;
import src.model.Player;

import java.util.ArrayList;

public interface GameControllerInterface {

    void play(GameControllerInterface game);
    int[] getPlayersInput();
    void setPlayersInput(int[] pInput);
    void createPlayers();
    Cell[][] initCells(int pSizeHeight, int pSizeLength);
    GameState playGame();
    Player addTurnSetActivePlayerDisplayBoard(Player pActive, int pHeight, int pLength);
    GameState moveValidAndSetStateMachine(Player pActive);
    GameState resetBoard(int pHeight, int pLength);
    void getValidMove();
    void resetCells(String pSymbolReset, int pSizeHeight, int pSizeLength);
    boolean checkIfGameWonColumnAndLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck);
    boolean checkIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign);
    GameState isGameOver();
    GameState treatPlayAgainChoice();
    String correctNewGameEntry();

    int getSizeHeight();

    int getSizeLength();
}
