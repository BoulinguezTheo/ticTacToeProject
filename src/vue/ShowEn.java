/*
 * Nom de classe : View
 *
 * Description   : Classe regroupant les fonctions spécifiques au bon affichage du jeu en console.
 *
 * Version       : 1.0
 *
 * Date          : 02/01/2023
 *
 * Copyright     : moi
 */
package src.vue;

import src.model.Cell;

public class ShowEn implements ShowInterface {


  public ShowEn() {

  }

  @Override
  public void displayGameChoice(){
    System.out.println("Welcome !");
    System.out.println("Choose a game :");
    System.out.println("1- TicTacToe");
    System.out.println("2- Power 4");
    System.out.println("3- Gomuko");
  }
  @Override
  public void displayBoard(int length, int height, Cell[][] cellsBoard) {
    System.out.println("-------------");
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < height; j++) {
        System.out.print(cellsBoard[i][j].getCell());
      }
      System.out.println("|");
      System.out.println("-------------");
    }
  }
  @Override
  public void playersTypeChoice(String playerNth, String pSymbol) {
    System.out.println("-Press 1 for a human player");
    System.out.println("-Press 2 for an artificial player");
    System.out.print("Setup " + playerNth + " player: ");
  }
  @Override
  public void playerMoveChoice(String coordinate) {
    System.out.print("Enter " + coordinate + ": ");
  }
  @Override
  public void displayInputError(){
    System.out.println("Error: please try again");
  }
  @Override
  public void boxIsFilled() {
    System.out.println("Box already filled, please try again");
  }
  @Override
  public void displayPlayerTurn(String pSymbolPlayer) {
    System.out.println("It's player '" + pSymbolPlayer + "' turn");
  }
  @Override
  public void displayWinner(String winner){
    System.out.println("Player '" + winner + "' has won!");
  }
  @Override
  public void displayDraw(){
    System.out.println("It's a draw");
  }
  @Override
  public void displayAskNewGame(){
    System.out.println("Do you want to play again? (Y / N)");
  }
  @Override
  public void displayExit(){
    System.out.println("Thanks for playing.");
    System.out.println("Bye bye, baby! \uD83D\uDC4B");
  }

}
