package src.model;

import src.controller.TicTacToe;

import java.util.ArrayList;

public interface GameInterface {
    void setBoardCell(Cell[][] pBoard);

    Cell[][] getBoardCell();

    void setOwner(int[] input, String pSymbol);

    int getTurns();

    void addTurn();


    boolean processInputColumnLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck, int[] inputCoordinates, TicTacToe pGame);
    boolean processInputDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign, int[] inputCoordinates,TicTacToe pGame);
    void createNewArrayOfCoordinates(ArrayList<ArrayList<int[]>> arrayToCheck, int[] inputCoordinates);
    void resetTurns();
}
