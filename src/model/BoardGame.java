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
import src.controller.GameControllerInterface;

public class BoardGame implements GameInterface{
    public Cell[][] board;
    public int turns;

    private int[] playersInput;

    public BoardGame(){
        turns = 0;
    }

    public void setBoardCell(Cell[][] pBoard){
        board = pBoard;
    }

    public Cell[][] getBoardCell(){
        return board;
    }

    public void setPlayerInput(int[] pInput){
        this.playersInput = pInput;
    }
    public int[] getPlayersInput(){
        return this.playersInput;
    }
    public void setOwner(String pSymbol){
        this.board[playersInput[1]][playersInput[0]].representation = pSymbol;
    }

    public int getTurns(){
        return this.turns;
    }

    public void addTurn(){
        this.turns++;
    }

    public void resetTurns(){
        this.turns = 0;
    }
    /**
     *  Traite les coordonnées jouées pour les lignes et colonnes possibles pour gagner la partie.
     */
    public boolean processInputColumnLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck, int[] inputCoordinates, GameControllerInterface pGame){
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
    public boolean processInputDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign, int[] inputCoordinates, GameControllerInterface pGame){
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
