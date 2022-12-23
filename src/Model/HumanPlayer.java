package src.Model;

import java.util.Scanner;

import src.Controller.UserInteraction;

public class HumanPlayer extends Player{
    private String symbol;
    final protected String type = "Human";

    public HumanPlayer(String pSymbol){
        this.symbol = pSymbol;
    }
    @Override
    public String getType(){
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
