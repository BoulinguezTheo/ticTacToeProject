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

import src.Factory;
import src.model.*;
import src.vue.ShowInterface;
import src.vue.UserInteractionInterface;

public class TicTacToe implements GameControllerInterface {
    private final int WINCONDITION = 3;
    private final int ENDGAMEBYTURNS = 9;
    private final int TUPLEX = 0; 
    private final int TUPLEY = 1;
    private final int SIZEHEIGHT = 3;
    private final int SIZELENGTH = 3;
    private Player player1;
    private Player player2;
    private Player activePlayer;
    private GameFunction gameStateMachine;
    private UserInteractionInterface interaction;
    private ShowInterface printer;
    private BoardInterface boardGame;

    public TicTacToe(ShowInterface pPrinter, UserInteractionInterface pInteraction) {
        // Initiate Objects
        this.boardGame = Factory.createBoard();
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
        String symbolPlayer1 = "X";
        String symbolPlayer2 = "O";
        this.player1 = createPlayer(interaction.askPlayerType("1st", symbolPlayer1, this), symbolPlayer1);
        this.player2 = createPlayer(interaction.askPlayerType("2nd", symbolPlayer2, this), symbolPlayer2);
        //SetupBoardGame
        boardGame.setBoardCell(initCells());
        return GameFunction.PLAY;
    }
    @Override
    public Player createPlayer(String pPlayer, String pSymbol){
        return (pPlayer.equals("1")) ? Factory.createHumanPlayer(pSymbol) : Factory.createArtificialPlayer(pSymbol);
    }
    @Override
    public Cell[][] initCells(){
        var cells = Factory.createCellBoard(this.SIZELENGTH, this.SIZEHEIGHT);
        for (int i = 0; i < this.SIZEHEIGHT; i++){
            for (int j = 0; j < this.SIZELENGTH; j++){
                cells[i][j] = Factory.createCell();
            }
        }
        return cells;
    }
    @Override
    public GameFunction play(){
        this.activePlayer = addTurnSetActivePlayerDisplayBoard();
        return moveValidAndSetStateMachine();
    }
    public Player addTurnSetActivePlayerDisplayBoard() {
        this.boardGame.addTurn();
        printer.displayBoard(this.SIZELENGTH, this.SIZEHEIGHT, this.boardGame.getBoardCell());
        return (this.activePlayer == this.player1) ? this.player2 : this.player1;
    }
    @Override
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
            inputPlayer = this.activePlayer.getMoveFromPlayer(interaction, printer);
        } while (isBoxFilled(inputPlayer));
        return inputPlayer;
    }
    @Override
    public boolean  isBoxFilled(int[] input){
        int inputColumn = input[TUPLEY];
        int inputLine = input[TUPLEX];
        if(boardGame.getBoardCell()[inputColumn][inputLine].getRepresentation().equalsIgnoreCase(" ")){
            return false;
        } else {
            if (this.activePlayer.getType().equals("Human")){
                this.printer.displayBoxIsFilled();
            }
            return true;
        }
    }
    /**
     *  Fonction permettant de déterminer si la partie est gagnée
     */
    @Override
    public GameFunction isGameOver(){
        if(isWinner()){
            printer.displayBoard(this.SIZELENGTH, this.SIZEHEIGHT, boardGame.getBoardCell());
            printer.displayWinner(activePlayer.getSymbol());
            return GameFunction.ENDGAME;
        } else if (this.boardGame.getTurns() == ENDGAMEBYTURNS){
            printer.displayBoard(this.SIZELENGTH, this.SIZEHEIGHT, boardGame.getBoardCell());
            printer.displayDraw();
            return GameFunction.ENDGAME;
        }
        return GameFunction.PLAY;
    }
    @Override
    public boolean isWinner(){
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
    @Override
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
    @Override
    public void getPlayerTypeChoice(String playerNth, String pSymbol){
        this.printer.displayPlayersTypeChoice(playerNth,pSymbol);
    }


}
