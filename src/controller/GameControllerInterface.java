package src.controller;

import src.model.Cell;
import src.model.Player;
import src.vue.ShowInterface;
import src.vue.UserInteractionInterface;

import java.util.ArrayList;

public interface GameControllerInterface {


    GameFunction initGameFunctions();
    Player[] createPlayers();
    Cell[][] initCells();
    GameState playGame(GameState pStateMachine);
    Player addTurnSetActivePlayerDisplayBoard();
    GameFunction moveValidAndSetStateMachine();
    int[] getValidMove();
    boolean  isBoxFilled(int[] input);
    boolean checkIfGameWonColumnAndLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck);
    boolean checkIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign);
    GameFunction isGameOver();

//    GameFunction resetBoard(int pHeight, int pLength);
//    void getValidMove();
//    void resetCells(String pSymbolReset, int pSizeHeight, int pSizeLength);
//    GameFunction treatPlayAgainChoice();
//
//    int getSizeHeight();
//
//    int getSizeLength();
//
//    GameState playGame();
}
