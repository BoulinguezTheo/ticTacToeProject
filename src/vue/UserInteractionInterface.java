package src.vue;
import src.controller.GameControllerInterface;
import src.model.BoardInterface;

public interface UserInteractionInterface {

    String askPlayerType(String playerNth, String pSymbol);

    String askPlayerMove(String coordinate);

    String getPlayAgainChoice();
    String getGameChoice();

    void setBoardGame(BoardInterface boardGame);
}
