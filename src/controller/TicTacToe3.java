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

import src.Factory;
import src.model.BoardGame;
import src.model.Cell;
import src.model.BoardInterface;
import src.model.Player;
import src.vue.ShowInterface;
import src.vue.UserInteractionInterface;

import java.util.ArrayList;

public class TicTacToe3 implements GameControllerInterface {
    private final int WINCONDITION = 3;
    private final int ENDGAMEBYTURNS = 9;
    private final int TUPLEX = 0;
    private final int TUPLEY = 1;
    private final int sizeHeight = 3;
    private final int sizeLength = 3;
    private Player player1;
    private Player player2;
    private Player activePlayer;
    private GameFunction gameStateMachine;
    private UserInteractionInterface interaction;
    private ShowInterface printer;

    private BoardInterface boardGame;

    public TicTacToe3() {
        // Initiate Objects
        this.boardGame = new BoardGame();
        //boardGame.setBoardCell(super.initCells(this.sizeHeight, this.sizeLength));
        this.gameStateMachine = GameFunction.INITGAME;
    }
    public TicTacToe3(ShowInterface pPrinter, UserInteractionInterface pInteraction) {
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
    public Player createPlayer(String pPlayer, String pSymbol){
        return (pPlayer.equals("1")) ? Factory.createHumanPlayer(pSymbol) : Factory.createArtificialPlayer(pSymbol);
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
        this.activePlayer = addTurnSetDisplayBoardSetActivePlayer();
        return moveValidAndSetStateMachine();
    }
    public Player addTurnSetDisplayBoardSetActivePlayer() {
        this.boardGame.addTurn();
        printer.displayBoard(this.sizeLength, this.sizeHeight, this.boardGame.getBoardCell());
        return (this.activePlayer == this.player1) ? this.player2 : this.player1;
    }
    public GameFunction moveValidAndSetStateMachine(){
        this.boardGame.setPlayerInput(getAvailableMove());
        this.boardGame.setOwner(this.activePlayer.getSymbol());
        return isGameOver();
    }
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
    public GameFunction isGameOver(){
        if(isWinner()){
            printer.displayBoard(this.sizeLength, this.sizeHeight, boardGame.getBoardCell());
            printer.displayWinner(activePlayer.getSymbol());
            return GameFunction.ENDGAME;
        } else if (this.boardGame.getTurns() == ENDGAMEBYTURNS){
            printer.displayBoard(this.sizeLength, this.sizeHeight, boardGame.getBoardCell());
            printer.displayDraw();
            return GameFunction.ENDGAME;
        }
        return GameFunction.PLAY;
    }

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
            int[] getTuple = array.get(0);
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
            if (array.size() == this.WINCONDITION){
                return true;
            }
        }
        if (!entered){
            boardGame.createNewArrayOfCoordinates(arrayToCheck, playersInput);
        }
        return false;
    }

    public void getPlayerTypeChoice(String playerNth, String pSymbol){
        this.printer.displayPlayersTypeChoice(playerNth,pSymbol);
    }


}
