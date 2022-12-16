package src;

import java.util.Scanner;

public class HumanPlayer extends Player{
    private String symbol;

    public HumanPlayer(String pSymbol){
        this.symbol = pSymbol;
    }
    @Override
    protected String getType(){
        return "Human";
    }

    @Override
    public int[] getMoveFromPlayer() {
        //Check if box is occupied
        int [] playerEntries = askPlayerInput();
        return playerEntries;
    }

    public String getSymbol(){
        return this.symbol;
    }

}
