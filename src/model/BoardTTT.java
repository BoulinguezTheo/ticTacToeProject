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

public class BoardTTT implements BoardInterface {
    private final int WINCONDITION = 3;
    private final int ENDGAMEBYTURNS = 9;
    private final int SIZEHEIGHT = 3;
    private final int SIZELENGTH = 3;
    private Player player1;
    private Player player2;
    private Player activePlayer;
    public Cell[][] board;
    public int turns;
    private int[] playersInput;


/*
 * CONSTRUCTOR
 **/
    public BoardTTT(){
        this.turns = 0;
    }

    public void setBoardCell(Cell[][] pBoard){
        board = pBoard;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }
    public void setPlayerInput(int[] pInput){
        this.playersInput = pInput;
    }
    public void setOwner(String pSymbol){
        this.board[playersInput[1]][playersInput[0]].setRepresentation(pSymbol);
    }
    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public Player getActivePlayer() {
        return activePlayer;
    }
    public Cell[][] getBoardCell(){
        return board;
    }
    public int getWinCondition() {
        return WINCONDITION;
    }
    public int getEndGameByTurns() {
        return ENDGAMEBYTURNS;
    }
    public int getSizeHeight() {
        return SIZEHEIGHT;
    }
    public int getSizeLength() {
        return SIZELENGTH;
    }
    public int[] getPlayersInput(){
        return this.playersInput;
    }

    public int getTurns(){
        return this.turns;
    }

    public void addTurn(){
        this.turns++;
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
        ArrayList<int[]> array = new ArrayList<>();
        array.add(inputCoordinates);
        arrayToCheck.add(array);
    }
}
