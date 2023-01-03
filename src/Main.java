package src;

import src.controller.GameController;
import src.controller.TicTacToe;
import src.model.BoardGame;
public class Main {
    public static void main(String[] args) {
        GameController ticTacToe = new TicTacToe();
        ticTacToe.playGame();
    }
}