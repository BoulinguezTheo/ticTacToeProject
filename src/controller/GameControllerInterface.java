package src.controller;

import src.model.BoardInterface;
import src.model.Cell;
import src.model.Player;
import java.util.ArrayList;

public interface GameControllerInterface {
    GameFunction initGame();


    Cell[][] initCells();

    GameState playGame(GameState pStateMachine);

    GameFunction play();


    Player addTurnSetDisplayBoardSetActivePlayer();

    GameFunction moveValidAndSetStateMachine();


    int[] getAvailableMove();

    boolean isBoxFilled(int[] input);

    boolean checkIfGameWonColumnAndLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck);

    boolean checkIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign);

    GameFunction isGameOver();
//

    boolean isWinner();

    Player createPlayer(String pPlayer, String pSymbol);

    BoardInterface getBoardGame();
}
