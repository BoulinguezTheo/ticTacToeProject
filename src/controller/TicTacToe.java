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
import java.util.Random;

import src.Factory;
import src.model.*;
import src.vue.MenuObserver;
import src.vue.UserInteractionInterface;
import src.vue.View;
import src.vue.ViewEn;

public class TicTacToe implements GameControllerInterface, MenuObserver {

    private final int TUPLEX = 0; 
    private final int TUPLEY = 1;

    private GameFunction gameStateMachine;
    private View view;

    public BoardInterface getBoardGame() {
        return boardGame;
    }

    private BoardInterface boardGame;

    public TicTacToe(View pView) {
        // Initiate Objects
        this.boardGame = Factory.createBoard();
        this.gameStateMachine = GameFunction.INITGAME;
        this.view = pView;
        this.view.setBoardGame(this.boardGame);
        this.view.addMenuOberserver(this);
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
                    return GameState.EXIT;
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
        boardGame.setPlayer1(createPlayer(view.askPlayerType("1st", symbolPlayer1), symbolPlayer1));
        boardGame.setPlayer2(createPlayer(view.askPlayerType("2nd", symbolPlayer2), symbolPlayer2));
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
        int sizeHeight = boardGame.getSizeHeight();
        int sizeLength = boardGame.getSizeLength();
        var cells = Factory.createCellBoard(sizeLength, sizeHeight);
        for (int i = 0; i < sizeHeight; i++){
            for (int j = 0; j < sizeLength; j++){
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
         boardGame.setActivePlayer(addTurnSetDisplayBoardSetActivePlayer());
        return moveValidAndSetStateMachine();
    }
    /**
     *  Adds a turn to the turn iterator, displays the board at the beginning of a turn and returns the active player for the turn
     */
    public Player addTurnSetDisplayBoardSetActivePlayer() {
        this.boardGame.addTurn();
        this.view.displayBoard();
        return (boardGame.getActivePlayer() == boardGame.getPlayer1()) ? boardGame.getPlayer2() : boardGame.getPlayer1();
    }
    /**
     *  Gets the move played, set the move in the board and returns if the game is over
     */
    @Override
    public GameFunction moveValidAndSetStateMachine(){
        int[] exit = {-1, -1};
        int[] inputPlayer = getAvailableMove();
        if (inputPlayer[0] == -1) {
            return GameFunction.EXIT;
        }
        this.boardGame.setPlayerInput(inputPlayer);
        this.boardGame.setOwner(boardGame.getActivePlayer().getSymbol());
        return isGameOver();
    }
    /**
     *  Displays which player is to play, makes sure the move is valid and returns coordinates of the move played
     */
    @Override
    public int[] getAvailableMove(){
        int[] inputPlayer;
        int[] exit = {-1, -1};
        this.view.displayPlayerTurn(boardGame.getActivePlayer().getSymbol());
        do {
            inputPlayer = getMove();
            if (inputPlayer[0] == -1){
                System.out.println("test");
                return inputPlayer;
            }
        } while (isBoxFilled(inputPlayer));
        return inputPlayer;
    }
    public int[] getMove(){
        return boardGame.getActivePlayer().getType().equalsIgnoreCase("bot") ? generateRandomInput() : getValidPlayerInput();
    }
    public int[] generateRandomInput(){
        Random randomInt = new Random();
        int column = randomInt.nextInt(0, 3);
        int line = randomInt.nextInt(0, 3);
        return new int[] {column, line};
    }
    public int[] getValidPlayerInput() {
        boolean validColumn, validLine;
        String column, line;

        //Check if column input is valid
        do {
            this.view.displayPlayerMoveChoice("column");
            column = view.askPlayerMove("column");
            validColumn = validInputUser(column);
        } while (!validColumn);

        //Check if line input is valid
        do {
            this.view.displayPlayerMoveChoice("line");
            line = view.askPlayerMove("line");
            validLine = validInputUser(line);
        } while (!validLine);

        if(column.equalsIgnoreCase("exit") && line.equalsIgnoreCase("exit")){
            return new int[]{-1, -1};
        }

        return new int[]{Integer.parseInt(column), Integer.parseInt(line)};
    }
    public boolean  validInputUser(String input) {
        if(input.equalsIgnoreCase("exit")){return true;}
        //Check if the input is valid
        if (!input.equals("0") && !input.equals("1") && !input.equals("2")) {
            this.view.displayInputError();
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
            if (boardGame.getActivePlayer().getType().equals("Human")){
                this.view.displayBoxIsFilled();
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
            this.view.displayBoard();
            this.view.displayWinner(boardGame.getActivePlayer().getSymbol());
            return GameFunction.ENDGAME;
        } else if (this.boardGame.getTurns() == boardGame.getEndGameByTurns()){
            this.view.displayBoard();
            this.view.displayDraw();
            return GameFunction.ENDGAME;
        }
        return GameFunction.PLAY;
    }
    /**
     *  Checks if the game is over by win.
     */
    @Override
    public boolean isWinner(){
        int coordinateLines = 0;
        int coordinateColumn = 1;
        int signDiagXPlusOne = -1;
        int signDiagXMinusOne = 1;
        Player activePlayer = boardGame.getActivePlayer();
        return boardGame.processInputColumnLines(activePlayer.getLineArrays(), coordinateLines, this.boardGame.getPlayersInput(), this)
                || boardGame.processInputColumnLines(activePlayer.getColumnArrays(), coordinateColumn, this.boardGame.getPlayersInput(), this)
                || boardGame.processInputDiags(activePlayer.getDiagXMinusOneArrays(), signDiagXPlusOne, this.boardGame.getPlayersInput(), this)
                || boardGame.processInputDiags(activePlayer.getDiagXPlusOneArrays(), signDiagXMinusOne, this.boardGame.getPlayersInput(), this);
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
            if (array.size() == boardGame.getWinCondition()){
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
        int getArrayNum = 0;
        boolean entered = false;
        for(ArrayList<int[]> array : arrayToCheck){
            int[] getTuple = array.get(getArrayNum);
            int coordinateToFitX = (x - getTuple[TUPLEX]);
            int coordinateToFitY = sign * (y - getTuple[TUPLEY]);
            if(coordinateToFitX == coordinateToFitY){
                array.add(playersInput);
            }
            if (array.size() == boardGame.getWinCondition()){
                return true;
            }
        }
        if (!entered){
            boardGame.createNewArrayOfCoordinates(arrayToCheck, playersInput);
        }
        return false;
    }

    @Override
    public void onCloseAsked() {
        System.out.println("Close");
    }

    @Override
    public void onQuitAsked() {
        System.out.println("Quit");
        this.gameStateMachine=GameFunction.EXIT;
    }

    @Override
    public void onLanguageAsked() {
        System.out.println("Language");

    }
}
