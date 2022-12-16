package src;

import java.util.Random;

public class ArtificialPlayer extends Player {
    private String symbol;

    public ArtificialPlayer(String pSymbol){
        this.symbol = pSymbol;
    }
    @Override
    protected String getType(){
        return "Bot";
    }

    @Override
    protected int[] getMoveFromPlayer() {
        //Check if box is occupied
        int [] playerEntries = generateRandomInputs();
        return playerEntries;
    }

    private int[] generateRandomInputs(){
        Random randomInt = new Random();
        int column = randomInt.nextInt(0, 3);
        int line = randomInt.nextInt(0, 3);
        return new int[] {column, line};
    }

    public String getSymbol(){
        return this.symbol;
    }

}
