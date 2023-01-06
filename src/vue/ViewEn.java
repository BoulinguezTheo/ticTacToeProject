package src.vue;

import src.controller.GameControllerInterface;
import src.model.BoardInterface;
import src.model.Cell;

import java.util.Scanner;

public class ViewEn extends View {
    Scanner setupPlayersScanner;
    Scanner moveScanner;
    Scanner newGameScanner;
    Scanner gameChoiceScanner;

    public void setBoardGame(BoardInterface boardGame) {
        this.boardGame = boardGame;
    }

    BoardInterface boardGame;

    public ViewEn(){
        setupPlayersScanner = new Scanner(System.in);
        moveScanner = new Scanner(System.in);
        newGameScanner = new Scanner(System.in);
        gameChoiceScanner = new Scanner(System.in);
    }

    @Override
    public void displayBoard() {
        Cell[][] cellsBoard = boardGame.getBoardCell();
        System.out.println("-------------");
        for (int i = 0; i < boardGame.getSizeLength(); i++) {
            for (int j = 0; j < boardGame.getSizeHeight(); j++) {
                System.out.print(cellsBoard[i][j].getCell());
            }
            System.out.println("|");
            System.out.println("-------------");
        }
    }

    @Override
    public void displayPlayersTypeChoice(String playerNth, String pSymbol) {
        System.out.println("-Press 1 for a human player");
        System.out.println("-Press 2 for an artificial player");
        System.out.print("Setup " + playerNth + " player: ");
    }

    @Override
    public void displayPlayerMoveChoice(String coordinate) {
        System.out.print("Enter " + coordinate + ": ");
    }

    @Override
    public void displayInputError() {
        System.out.println("Error: please try again");
    }

    @Override
    public void displayBoxIsFilled() {
        System.out.println("Box already filled, please try again");
    }

    @Override
    public void displayPlayerTurn(String pSymbolPlayer) {
        System.out.println("It's player '" + pSymbolPlayer + "' turn");
    }

    @Override
    public void displayWinner(String winner) {
        System.out.println("Player '" + winner + "' has won!");
    }

    @Override
    public void displayDraw() {
        System.out.println("It's a draw");
    }

    @Override
    public void displayAskNewGame() {
        System.out.println("Do you want to play again? (Y / N)");
    }

    @Override
    public void displayAskOtherGame() {
        System.out.println("Do you want to play another game? (Y/N)");
    }

    @Override
    public void displayExit() {
        System.out.println("Thanks for playing.");
        System.out.println("Bye bye, baby! \uD83D\uDC4B");
    }

    @Override
    public void displayGameChoice() {
        System.out.println("Welcome !");
        System.out.println("Choose a game :");
        System.out.println("1- TicTacToe");
        System.out.println("2- Power 4");
        System.out.println("3- Gomuko");
    }

    @Override
    public String askPlayerType(String playerNth, String pSymbol) {
        String player;
        do {
            displayPlayersTypeChoice(playerNth, pSymbol);
            player = this.setupPlayersScanner.nextLine();
        }while (!player.equals("1") && !player.equals("2"));
        //Need close scanner but don't know where
        return player;
    }

    @Override
    public String askPlayerMove(String coordinate) {
        return moveScanner.nextLine();
    }

    @Override
    public String getPlayAgainChoice() {
        return newGameScanner.nextLine();
    }

    @Override
    public String getGameChoice() {
        return this.gameChoiceScanner.nextLine();
    }
}
