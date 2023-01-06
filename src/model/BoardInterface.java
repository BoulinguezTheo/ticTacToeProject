package src.model;
import src.controller.GameControllerInterface;
import java.util.ArrayList;

public interface BoardInterface {
    void setBoardCell(Cell[][] pBoard);

    Cell[][] getBoardCell();
    void setPlayerInput(int[] validMove);
    int[] getPlayersInput();
    int getWinCondition();
    int getEndGameByTurns();
    int getSizeHeight();
    int getSizeLength();
    Player getPlayer1();
    Player getPlayer2();
    Player getActivePlayer();
    void setOwner(String pSymbol);
    int getTurns();

    void setPlayer1(Player player1);

    void setPlayer2(Player player2);

    void setActivePlayer(Player activePlayer);

    void addTurn();

    boolean processInputColumnLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck, int[] inputCoordinates, GameControllerInterface pGame);
    boolean processInputDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign, int[] inputCoordinates, GameControllerInterface pGame);
    void createNewArrayOfCoordinates(ArrayList<ArrayList<int[]>> arrayToCheck, int[] inputCoordinates);
}
