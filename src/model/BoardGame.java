/*
 * Nom de classe : BoardGame
 *
 * Description   : Classe regroupant les fonctions permettant de traiter et stocker les coups joués,
 *                 créer des joueurs et d'afficher sur le plateau de jeu les pions joués peut importe le type de jeu.
 *
 * Version       : 1.0
 *
 * Date          : 02/01/2023
 *
 * Copyright     : moi
 */
package src.model;

import java.util.ArrayList;

import src.controller.TicTacToe;

public class BoardGame {
    public Cell[][] board;
    public int turns;


    public BoardGame(){
        turns = 0;
    }
    public void setBoardCell(Cell[][] pBoard){
        board = pBoard;
    }

    public Cell[][] getBoardCell(){
        return board;
    }

    public void setOwner(int[] input, Player activePlayer){
        this.board[input[1]][input[0]].representation = activePlayer.getSymbol();
    }
    /**
     *  Traite les coordonnées jouées pour les lignes et colonnes possibles pour gagner la partie.
     */
    public boolean processInputColumnLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck, int[] inputCoordinates, TicTacToe pGame){
        if (arrayToCheck.size() == 0){
            createNewArrayOfCoordinates(arrayToCheck, inputCoordinates);
            return false;
        } else {
            return pGame.checkIfGameWonColumnAndLines(arrayToCheck,coordinateToCheck);
        }
    }
    /**
     *  Traite les coordonnées jouées pour les diagonales possibles pour gagner la partie.
     */
    public boolean processInputDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign, int[] inputCoordinates,TicTacToe pGame){
        if (arrayToCheck.size() == 0){
            createNewArrayOfCoordinates(arrayToCheck, inputCoordinates);
            return false;
        } else {
            return pGame.checkIfGameWonDiags(arrayToCheck, sign);
        }
    }
    /**
     *  Créer un nouvel array si la coordonnée du coup n'appartient pas à une diagonale, ligne ou colonne possible.
     */
    public void createNewArrayOfCoordinates(ArrayList<ArrayList<int[]>> arrayToCheck, int[] inputCoordinates){
        ArrayList<int[]> array = new ArrayList<int[]>();
        array.add(inputCoordinates);
        arrayToCheck.add(array);
    }

    

}