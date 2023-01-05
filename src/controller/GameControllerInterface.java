package src.controller;

import src.Factory;
import src.model.Cell;
import src.model.Player;
import java.util.ArrayList;

public interface GameControllerInterface {
    GameFunction initGame();
    Cell[][] initCells();
    GameState playGame(GameState pStateMachine);
    GameFunction play();
    Player addTurnSetActivePlayerDisplayBoard();
    GameFunction moveValidAndSetStateMachine();
    int[] getValidMove();
    boolean  isBoxFilled(int[] input);
    boolean checkIfGameWonColumnAndLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck);
    boolean checkIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign);
    GameFunction isGameOver();
    boolean isWinner();
    void getPlayerTypeChoice(String playerNth, String pSymbol);
    Player createPlayer(String pPlayer, String pSymbol);
}
