package src.vue;

import src.model.Cell;

public interface ShowInterface {
    void displayBoard(int length, int height, Cell[][] cellsBoard);
    void displayPlayersTypeChoice(String playerNth, String pSymbol);
    void displayPlayerMoveChoice(String coordinate);
    void displayInputError();
    void displayBoxIsFilled();
    void displayPlayerTurn(String pSymbolPlayer);
    void displayWinner(String winner);
    void displayDraw();
    void displayAskNewGame();
    void displayAskOtherGame();
    void displayExit();
    void displayGameChoice();
}
