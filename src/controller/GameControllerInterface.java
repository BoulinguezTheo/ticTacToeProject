package src.controller;

import src.model.Cell;
import src.model.Player;
import src.vue.ShowInterface;
import src.vue.UserInteractionInterface;

import java.util.ArrayList;

public interface GameControllerInterface {



    void setPlayersInput(int[] pInput);
    protected GameFunction initGameFunctions(UserInteractionInterface pInteractor, ShowInterface pPrinter)
    Player[] createPlayers(UserInteractionInterface pInteractor);
    Cell[][] initCells();
    GameState playGame(UserInteractionInterface pInteractor, ShowInterface pPrinter);
    Player addTurnSetActivePlayerDisplayBoard(UserInteractionInterface pInteractor, ShowInterface pPrinter);
    GameFunction moveValidAndSetStateMachine(UserInteractionInterface pInteractor, ShowInterface pPrinter);
    int[] getValidMove(ShowInterface pPrinter, UserInteractionInterface pInteractor);
    boolean  isBoxFilled(int[] input, UserInteractionInterface pInteractor);
    GameFunction resetBoard(int pHeight, int pLength);
    void getValidMove();
    void resetCells(String pSymbolReset, int pSizeHeight, int pSizeLength);
    boolean checkIfGameWonColumnAndLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck);
    boolean checkIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign);
    GameFunction isGameOver(ShowInterface pPrinter);
    GameFunction treatPlayAgainChoice();
    String correctNewGameEntry();

    int getSizeHeight();

    int getSizeLength();

    GameState playGame();
}
