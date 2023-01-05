package src.vue;
import src.model.Player;

public interface UserInteractionInterface {

    Player setupPlayers(String playerNth, String pSymbol, ShowInterface pPrinter);

    String askPlayerMove(String coordinate);

    void getDisplayInputError();

    void getDisplayBoxIsFilled();

    String getPlayAgainChoice();
    String getGameChoice();
    String getAksOtherGame();

}
