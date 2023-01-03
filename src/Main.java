package src;

import src.controller.GameControllerInterface;
import src.controller.TicTacToe;

public class Main {
    public static void main(String[] args) {
        GameControllerInterface game= new TicTacToe();
        game.play(game);
    }
}