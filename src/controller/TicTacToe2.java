/*
 * Nom de classe : TicTacToe
 *
 * Description   : Classe regroupant les fonctions spécifiques au bon fonctionnement et respectant les règles d'un jeu
 *                 de tictactoe.
 *
 * Version       : 1.0
 *
 * Date          : 02/01/2023
 *
 * Copyright     : moi
 */
package src.controller;

import src.model.BoardGame;
import src.model.Cell;
import src.model.GameInterface;
import src.model.Player;
import src.vue.ShowInterface;
import src.vue.UserInteractionInterface;

import java.util.ArrayList;

public class TicTacToe2 implements GameControllerInterface {
    private final int winCondition = 3;
    private final int endGameByTurns = 9;
    private int sizeHeight;
    private int sizeLength;
    private Player player1;
    private Player player2;
    private Player activePlayer;
    private GameFunction gameStateMachine;
    private UserInteractionInterface interaction;
    private ShowInterface printer;

    private GameInterface boardGame;

    public TicTacToe2() {
        //Initiate variables
        this.sizeHeight = 3;
        this.sizeLength = 3;
        // Initiate Objects
        this.boardGame = new BoardGame();
        //boardGame.setBoardCell(super.initCells(this.sizeHeight, this.sizeLength));
        this.gameStateMachine = GameFunction.INITGAME;
    }
    public TicTacToe2(ShowInterface pPrinter, UserInteractionInterface pInteraction) {
        //Initiate variables
        this.sizeHeight = 3;
        this.sizeLength = 3;
        // Initiate Objects
        this.boardGame = new BoardGame();
        //boardGame.setBoardCell(super.initCells(this.sizeHeight, this.sizeLength));
        this.gameStateMachine = GameFunction.INITGAME;
        this.printer = pPrinter;
        this.interaction = pInteraction;
    }
    @Override
    public GameState playGame(GameState pStateMachine) {
        while (this.gameStateMachine != GameFunction.EXIT) {
            switch (this.gameStateMachine) {
                case INITGAME:
                    this.gameStateMachine = initGameFunctions();
                    break;
                case PLAY:
                    this.gameStateMachine = play();
                    break;
                case ENDGAME:
                    this.gameStateMachine = GameFunction.EXIT;
                    break;
                case EXIT:
                    break;
            }
        }
        return GameState.NEWGAME;
    }
    @Override
    public GameFunction initGameFunctions() {
        //Setup players
        Player[] players = createPlayers();
        this.player1 = players[0];
        this.player2 = players[1];
        //SetupBoardGame
        boardGame.setBoardCell(initCells());
        return GameFunction.PLAY;
    }
    @Override
    public Player[] createPlayers(){
        Player playerOne = interaction.setupPlayers("1st", "X");
        Player playerTwo = interaction.setupPlayers("2nd", "O");
        return new Player[]{playerOne, playerTwo};
    }
    @Override
    public Cell[][] initCells(){
        Cell[][] cells = new Cell[this.sizeLength][this.sizeHeight];
        for (int i = 0; i < this.sizeHeight; i++){
            for (int j = 0; j < this.sizeLength; j++){
                cells[i][j] = new Cell();
            }
        }
        return cells;
    }
    public GameFunction play(){
        this.activePlayer = addTurnSetActivePlayerDisplayBoard();
        return moveValidAndSetStateMachine();
    }
    public Player addTurnSetActivePlayerDisplayBoard() {
        this.boardGame.addTurn();
        printer.displayBoard(this.sizeLength, this.sizeHeight, this.boardGame.getBoardCell());
        return (this.activePlayer == this.player1) ? this.player2 : this.player1;
    }
    public GameFunction moveValidAndSetStateMachine(){
        this.boardGame.setPlayerInput(getValidMove());
        this.boardGame.setOwner(this.activePlayer.getSymbol());
        return isGameOver();
    }
    @Override
    public int[] getValidMove(){
        int[] inputPlayer;
        printer.displayPlayerTurn(this.activePlayer.getSymbol());
        do {
            inputPlayer = this.activePlayer.getMoveFromPlayer(interaction);
        } while (isBoxFilled(inputPlayer));
        return inputPlayer;
    }
    public boolean  isBoxFilled(int[] input){
        int inputColumn = input[1];
        int inputLine = input[0];
        if(boardGame.getBoardCell()[inputColumn][inputLine].representation == " "){
            return false;
        } else {
            if (this.activePlayer.getType().equals("Human")){
                interaction.getDisplayBoxIsFilled();
            }
            return true;
        }
    }
    /**
     *  Fonction permettant de déterminer si la partie est gagnée
     */
    public GameFunction isGameOver(){
        if(isWinner()){
            printer.displayBoard(this.sizeLength, this.sizeHeight, boardGame.getBoardCell());
            printer.displayWinner(activePlayer.getSymbol());
            return GameFunction.ENDGAME;
        } else if (this.boardGame.getTurns() == endGameByTurns){
            printer.displayBoard(this.sizeLength, this.sizeHeight, boardGame.getBoardCell());
            printer.displayDraw();
            return GameFunction.ENDGAME;
        }
        return GameFunction.PLAY;
    }

    private boolean isWinner(){
        return boardGame.processInputColumnLines(activePlayer.getLineArrays(), 0, this.boardGame.getPlayersInput(), this)
                || boardGame.processInputColumnLines(activePlayer.getColumnArrays(), 1, this.boardGame.getPlayersInput(), this)
                || boardGame.processInputDiags(activePlayer.getDiagXMinusOneArrays(), -1, this.boardGame.getPlayersInput(), this)
                || boardGame.processInputDiags(activePlayer.getDiagXPlusOneArrays(), 1, this.boardGame.getPlayersInput(), this);
    }
    /**
     *  Permet de déterminé si une ligne ou une colonne est gagnante
     */
    @Override
    public boolean checkIfGameWonColumnAndLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck){
        int[] playersInput = this.boardGame.getPlayersInput();
        boolean entered = false;
        for(ArrayList<int[]> array : arrayToCheck){
            int[] getTuple = array.get(0);
            int coordinateToFit = getTuple[coordinateToCheck];
            if(playersInput[coordinateToCheck] == coordinateToFit){
                array.add(playersInput);
                entered = true;
            }
            if (array.size() == this.winCondition){
                return true;
            }
        }
        if (!entered){
            boardGame.createNewArrayOfCoordinates(arrayToCheck, playersInput);
        }

        return false;
    }
    /**
     *  Fonction permettant de déterminer si une digonale est gagnante.
     */
    public boolean checkIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign){
        int[] playersInput = this.boardGame.getPlayersInput();
        int x = playersInput[0];
        int y = playersInput[1];
        boolean entered = false;
        for(ArrayList<int[]> array : arrayToCheck){
            int[] getTuple = array.get(0);
            int coordinateToFitX = (x - getTuple[0]);
            int coordinateToFitY = sign * (y - getTuple[1]);
            if(coordinateToFitX == coordinateToFitY){
                array.add(playersInput);
            }
            if (array.size() == this.winCondition){
                return true;
            }
        }
        if (!entered){
            boardGame.createNewArrayOfCoordinates(arrayToCheck, playersInput);
        }
        return false;
    }

    public int getSizeHeight(){
        return this.sizeHeight;
    }
    public int getSizeLength(){
        return this.sizeLength;
    }


}
