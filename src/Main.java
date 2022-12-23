package src;

import java.util.*;

import src.Controller.TicTacToe;
import src.Model.BoardGame;
public class Main {
    public static void main(String[] args) {
        BoardGame ticTacToe = new TicTacToe();
        ticTacToe.getPlayGame();
    }
}