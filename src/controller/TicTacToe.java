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

import java.util.ArrayList;

import src.model.BoardGame;
import src.model.Cell;
import src.model.GameInterface;
import src.model.Player;
import src.vue.ShowInterface;
import src.vue.UserInteractionInterface;

public class TicTacToe implements GameControllerInterface {
    private final int WINCONDITION = 3;
    private final int ENDGAMEBYTURNS = 9;
    private final int TUPLEX = 0;
    private final int TUPLEY = 1;
    private int SIZE_HEIGHT = 3;
    private int SIZE_LENGTH = 3;
    private Player player1;
    private Player player2;
    private Player activePlayer;
    private GameFunction gameStateMachine;
    private UserInteractionInterface interaction;
    private ShowInterface printer;
    private GameInterface boardGame;

    public TicTacToe() {
        // Initiate Objects
        this.boardGame = new BoardGame();
        this.gameStateMachine = GameFunction.INITGAME;
    }
    public TicTacToe(ShowInterface pPrinter, UserInteractionInterface pInteraction) {
        //Initiate variables
        this.SIZE_HEIGHT = 3;
        this.SIZE_LENGTH = 3;
        // Initiate Objects
        this.boardGame = new BoardGame();
        this.gameStateMachine = GameFunction.INITGAME;
        this.printer = pPrinter;
        this.interaction = pInteraction;
    }
    @Override
    public GameState playGame(GameState pStateMachine) {
        while (this.gameStateMachine != GameFunction.EXIT) {
            switch (this.gameStateMachine) {
                case INITGAME:
                    this.gameStateMachine = initGame();
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
    public GameFunction initGame() {
        //Setup players
        this.player1 = interaction.setupPlayers("1st", "X", this.printer);
        this.player2 = interaction.setupPlayers("2nd", "O", this.printer);
        //SetupBoardGame
        boardGame.setBoardCell(initCells());
        return GameFunction.PLAY;
    }
    @Override
    public Cell[][] initCells(){
        Cell[][] cells = new Cell[this.SIZE_LENGTH][this.SIZE_HEIGHT];
        for (int i = 0; i < this.SIZE_HEIGHT; i++){
            for (int j = 0; j < this.SIZE_LENGTH; j++){
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
        printer.displayBoard(this.SIZE_LENGTH, this.SIZE_HEIGHT, this.boardGame.getBoardCell());
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
        int inputColumn = input[TUPLEY];
        int inputLine = input[TUPLEX];
        if(boardGame.getBoardCell()[inputColumn][inputLine].getRepresentation().equalsIgnoreCase(" ")){
            return false;
        } else {
            if (this.activePlayer.getTYPE().equals("Human")){
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
            printer.displayBoard(this.SIZE_LENGTH, this.SIZE_HEIGHT, boardGame.getBoardCell());
            printer.displayWinner(activePlayer.getSymbol());
            return GameFunction.ENDGAME;
        } else if (this.boardGame.getTurns() == ENDGAMEBYTURNS){
            printer.displayBoard(this.SIZE_LENGTH, this.SIZE_HEIGHT, boardGame.getBoardCell());
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
            int[] getTuple = array.get(TUPLEX);
            int coordinateToFit = getTuple[coordinateToCheck];
            if(playersInput[coordinateToCheck] == coordinateToFit){
                array.add(playersInput);
                entered = true;
            }
            if (array.size() == this.WINCONDITION){
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
        int x = playersInput[TUPLEX];
        int y = playersInput[TUPLEY];
        boolean entered = false;
        for(ArrayList<int[]> array : arrayToCheck){
            int[] getTuple = array.get(0);
            int coordinateToFitX = (x - getTuple[TUPLEX]);
            int coordinateToFitY = sign * (y - getTuple[TUPLEY]);
            if(coordinateToFitX == coordinateToFitY){
                array.add(playersInput);
            }
            if (array.size() == this.WINCONDITION){
                return true;
            }
        }
        if (!entered){
            boardGame.createNewArrayOfCoordinates(arrayToCheck, playersInput);
        }
        return false;
    }


}
