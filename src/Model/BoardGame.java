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
package src.Model;

import java.util.ArrayList;

import src.Controller.UserInteraction;
import src.Vue.View;

public abstract class BoardGame {
    public Cell[][] boardGame;
    public  Player player1;
    public Player player2;
    public  UserInteraction interaction;
    public View printer;
    public int turns;

    public BoardGame(){
        this.interaction = new UserInteraction();
        this.printer = new View();
        createPlayers();
        turns = 0;
    }

    private void createPlayers(){
        this.player1 = this.interaction.setupPlayers("1st", "X");
        this.player2 = this.interaction.setupPlayers("2st", "O");
    }

    protected abstract void playGame();
    public void getPlayGame(){
        playGame();
    }

    public void setOwner(int[] input, Player activePlayer){
        this.boardGame[input[1]][input[0]].representation = activePlayer.getSymbol();
    }
    /**
     *  Traite les coordonnées jouées pour les lignes et colonnes possibles pour gagner la partie.
     */
    public boolean processInputColumnLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck, int[] inputCoordinates){
        if (arrayToCheck.size() == 0){
            createNewArrayOfCoordinates(arrayToCheck, inputCoordinates);
            return false;
        } else {
            return checkIfGameWonColumnAndLines(arrayToCheck,coordinateToCheck);
        }
    }
    /**
     *  Traite les coordonnées jouées pour les diagonales possibles pour gagner la partie.
     */
    public boolean processInputDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign, int[] inputCoordinates){
        if (arrayToCheck.size() == 0){
            createNewArrayOfCoordinates(arrayToCheck, inputCoordinates);
            return false;
        } else {
            return checkIfGameWonDiags(arrayToCheck, sign);
        }
    }

    protected abstract boolean checkIfGameWonColumnAndLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck);
    protected abstract boolean checkIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign);

    /**
     *  Créer un nouvel array si la coordonnée du coup n'appartient pas à une diagonale, ligne ou colonne possible.
     */
    protected void createNewArrayOfCoordinates(ArrayList<ArrayList<int[]>> arrayToCheck, int[] inputCoordinates){
        ArrayList<int[]> array = new ArrayList<int[]>();
        array.add(inputCoordinates);
        arrayToCheck.add(array);
    }

    

}
