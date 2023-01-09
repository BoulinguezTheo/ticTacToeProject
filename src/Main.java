package src;

import src.controller.AppController;
import src.model.BoardInterface;
import src.model.GameSerialization;
import src.model.Persistance;

public class Main {

    public static void main(String[] args) {
        AppController game = new AppController();
        game.run();

        BoardInterface boardCopy = game.getBoard();
        Persistance save = new GameSerialization();
        save.saveStateBoard(boardCopy);

    }
}