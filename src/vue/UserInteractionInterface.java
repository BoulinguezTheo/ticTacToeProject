package src.vue;
import src.controller.GameControllerInterface;

public interface UserInteractionInterface {

    String askPlayerType(String playerNth, String pSymbol, GameControllerInterface pController);

    String askPlayerMove(String coordinate);

    String getPlayAgainChoice();
    String getGameChoice();

}
