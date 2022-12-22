package src;

import java.util.Scanner;

public class HumanPlayer extends Player{
    private String symbol;
    final protected String type = "Human";

    public HumanPlayer(String pSymbol){
        this.symbol = pSymbol;
    }
    @Override
    protected String getType(){
        return this.type ;
    }

    @Override
    public int[] getMoveFromPlayer(UserInteraction interactor) {
        //Check if box is occupied
        int [] playerEntries = askPlayerInput(interactor);
        return playerEntries;
    }

    public String getSymbol(){
        return this.symbol;
    }

}
