package src.vue;

import src.model.Cell;

public interface ShowInterface {
    void displayBoard(int length, int height, Cell[][] cellsBoard);
    void playersTypeChoice(String playerNth, String pSymbol);
    void playerMoveChoice(String coordinate);
    void displayInputError();
    void boxIsFilled();
    void displayPlayerTurn(String pSymbolPlayer);
    void displayWinner(String winner);
    void displayDraw();
    void displayAskNewGame();
    void displayExit();
    void displayGameChoice();
}
