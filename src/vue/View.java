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

public class View {


  public View() {

  }

  public void  displayBoard(int length, int height, Cell[][] cellsBoard) {
    System.out.println("-------------");
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < height; j++) {
        System.out.print(cellsBoard[i][j].getCell());
      }
      System.out.println("|");
      System.out.println("-------------");
    }
  }

  public void playersTypeChoice(String playerNth, String pSymbol) {
    System.out.println("-Press 1 for a human player");
    System.out.println("-Press 2 for an artificial player");
    System.out.print("Setup " + playerNth + " player: ");
  }

  public void playerMoveChoice(String coordinate) {
    System.out.print("Enter " + coordinate + ": ");
  }
  public void displayInputError(){
    System.out.println("Error: please try again");
  }
  public void boxIsFilled() {
    System.out.println("Box already filled, please try again");
  }
  public void displayPlayerTurn(String pSymbolPlayer) {
    System.out.println("It's player '" + pSymbolPlayer + "' turn");
  }
  public void displayWinner(String winner){
    System.out.println("Player '" + winner + "' has won!");
  }
  public void displayDraw(){
    System.out.println("It's a draw");
  }

  public void displayAskNewGame(){
    System.out.println("Do you want to play again? (Y / N)");
  }
  public void displayExit(){
    System.out.println("Thanks for playing.");
    System.out.println("Bye bye, baby! \uD83D\uDC4B");
  }

}
