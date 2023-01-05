package src.controller;

import src.model.Cell;
import src.model.Player;
import java.util.ArrayList;

public interface GameControllerInterface {
    GameFunction initGame();
    Cell[][] initCells();
    GameState playGame(GameState pStateMachine);
    Player addTurnSetActivePlayerDisplayBoard();
    GameFunction moveValidAndSetStateMachine();
    int[] getValidMove();
    boolean  isBoxFilled(int[] input);
    boolean checkIfGameWonColumnAndLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck);
    boolean checkIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign);
    GameFunction isGameOver();
}
