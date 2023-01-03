package src;

import src.controller.GameControllerInterface;
import src.controller.TicTacToe;

public class Main {
    public static void main(String[] args) {
        GameControllerInterface ticTacToe = new TicTacToe();
        ticTacToe.play();
    }
}