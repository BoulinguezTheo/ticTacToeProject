package src.model;

import java.io.Serializable;

public interface Persistance {
    void saveStateBoard(BoardInterface pBoard);
    void savePlayerTurn(Player pActivePlayer);
}
