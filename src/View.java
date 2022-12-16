package src;


public class View {

  public View(){
    
  }
  protected void display(int length, int height, Cell[][] cellsBoard) {
    System.out.println("-------------");
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < height; j++) {
        System.out.print(cellsBoard[i][j].getCell());
      }
      System.out.println("|");
      System.out.println("-------------");
    }
  }

  protected void playersTypeChoice(String playerNth, String pSymbol){
          System.out.println("-Press 1 for a human player");
        System.out.println("-Press 2 for an artificial player");
        System.out.print("Setup " + playerNth +" player: ");
  }

  protected void playerMoveChoice(String coordinate){
      System.out.print("Enter " + coordinate + ": ");
  }

  protected void boxIsFilled(){
    System.out.println("Box already filled, please try again");
  }
}
