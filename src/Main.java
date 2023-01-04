package src;

import src.controller.AppController;
import src.controller.GameControllerInterface;
import src.controller.TicTacToe;

public class Main {

    public static void main(String[] args) {
        AppController game = new AppController();
        game.run();
    }
}