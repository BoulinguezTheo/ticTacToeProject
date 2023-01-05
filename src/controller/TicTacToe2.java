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

public class TicTacToe2 implements GameControllerInterface {
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

    public TicTacToe2(ShowInterface pPrinter, UserInteractionInterface pInteraction) {
        // Initiate Objects
        this.boardGame = Factory.createBoard();
        this.gameStateMachine = GameFunction.INITGAME;
        this.printer = pPrinter;
        this.interaction = pInteraction;
    }
    /**
     *  Switch states as the game progresses
     */
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
    /**
     *  Instantiate players, instantiate the board and switch state to the next
     */
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
    /**
     *  Creates one player of the type required by the user
     * @param pPlayer -> player type
     * @param pSymbol -> symbol assigned to the player
     */
    @Override
    public Player createPlayer(String pPlayer, String pSymbol){
        return (pPlayer.equals("1")) ? Factory.createHumanPlayer(pSymbol) : Factory.createArtificialPlayer(pSymbol);
    }
    /**
     *  Create the board array composed by Cell objects
     */
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
    /**
     *  set the active player for the turn and initiate a sequence of functions which compose a full turn for one player
     *  returns the state of the game, same state if game not over, next state of game if game over
     */
    @Override
    public GameFunction play(){
        this.activePlayer = addTurnSetDisplayBoardSetActivePlayer();
        return moveValidAndSetStateMachine();
    }
    /**
     *  Adds a turn to the turn iterator, displays the board at the beginning of a turn and returns the active player for the turn
     */
    public Player addTurnSetDisplayBoardSetActivePlayer() {
        this.boardGame.addTurn();
        printer.displayBoard(this.SIZELENGTH, this.SIZEHEIGHT, this.boardGame.getBoardCell());
        return (this.activePlayer == this.player1) ? this.player2 : this.player1;
    }
    /**
     *  Gets the move played, set the move in the board and returns if the game is over
     */
    @Override
    public GameFunction moveValidAndSetStateMachine(){
        this.boardGame.setPlayerInput(getAvailableMove());
        this.boardGame.setOwner(this.activePlayer.getSymbol());
        return isGameOver();
    }
    /**
     *  Displays which player is to play, makes sure the move is valid and returns coordinates of the move played
     */
    @Override
    public int[] getAvailableMove(){
        int[] inputPlayer;
        printer.displayPlayerTurn(this.activePlayer.getSymbol());
        do {
            inputPlayer = getValidPlayerInput();
        } while (isBoxFilled(inputPlayer));
        return inputPlayer;
    }
    public int[] getValidPlayerInput() {
        boolean validColumn, validLine;
        String column, line;

        //Check if column input is valid
        do {
            column = interaction.askPlayerMove("column");
            validColumn = validInputUser(column);
        } while (!validColumn);

        //Check if line input is valid
        do {
            printer.displayPlayerMoveChoice("line");
            line = interaction.askPlayerMove("line");
            validLine = validInputUser(line);
        } while (!validLine);

        return new int[]{Integer.parseInt(column), Integer.parseInt(line)};
    }
    public boolean  validInputUser(String input) {
        //Check if the input is valid
        if (!input.equals("0") && !input.equals("1") && !input.equals("2")) {
            printer.displayInputError();
            return false;
        }
        return true;
    }
    /**
     *  Checks if the cell played is available
     */
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
     *  Checks if the game is over, by turns (board filed up) or by win, displays the board after the move has been played
     *  and return the game's state.
     *
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
    /**
     *  Checks if the game is over by win.
     */
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
    /**
     *  Calls the printer in order to display the choice he has to make regarding the player type.
     */
    @Override
    public void getPlayerTypeChoice(String playerNth, String pSymbol){
        this.printer.displayPlayersTypeChoice(playerNth,pSymbol);
    }




}
