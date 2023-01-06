package src;

import src.model.*;
import src.vue.*;

public class Factory {
    public static Player createArtificialPlayer(String pSymbol){
        return new ArtificialPlayer(pSymbol);
    }
    public static Player createHumanPlayer(String pSymbol){
        return new HumanPlayer(pSymbol);
    }
    public static View createViewEn(){
        return new ViewEn();
    }
    public static BoardInterface createBoard(){
        return new BoardTTT();
    }
    public static Cell[][] createCellBoard(int pLength, int pHeight){
        return new Cell[pLength][pHeight];
    }
    public static Cell createCell(){
        return new Cell();
    }

}
