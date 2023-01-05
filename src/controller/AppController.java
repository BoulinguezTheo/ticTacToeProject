package src.controller;


import src.Factory;
import src.vue.ShowInterface;
import src.vue.UserInteraction;
import src.vue.ShowEn;
import src.vue.UserInteractionInterface;

public class AppController{

    private final String TICTACTOE = "1";
    private final String POWER4 = "2";
    private final String GOMUKO = "3";
    private ShowInterface printer;
    private GameControllerInterface game;
    private UserInteractionInterface interaction;
    private GameState stateMachine;


    
    public AppController(){
        this.printer = Factory.createPrinterEn();
        this.interaction = Factory.createInterator();
        this.stateMachine = GameState.CHOOSEGAME;
    }

    public void run(){
        String gameChoice = "0";
        while(stateMachine != GameState.EXIT){
            switch(stateMachine){
                case CHOOSEGAME ->  {gameChoice = askGameChoice(); this.stateMachine = GameState.INSTANTIATEGAME;}
                case INSTANTIATEGAME -> this.stateMachine = initGame(gameChoice);
                case PLAYING -> this.stateMachine = play();
                case NEWGAME -> this.stateMachine = askPlayAgain(true);
                case EXIT -> System.out.println();
            }
        }
        this.printer.displayExit();
    }
    private String askGameChoice() {
        this.printer.displayGameChoice();
        String input = this.interaction.getGameChoice();
        boolean correctInput = false;
        while (!correctInput) {
            if (input.equalsIgnoreCase("1") || input.equalsIgnoreCase("2") || input.equalsIgnoreCase("3")){
                correctInput = true;
            } else {
                this.printer.displayGameChoice();
                input = this.interaction.getGameChoice();
            }
        }
        return input;
    }
    private GameState initGame(String pGameChoice){
        switch(pGameChoice){
            case TICTACTOE:
                this.game = new TicTacToe(this.printer, this.interaction);
                break;
            case POWER4:
                this.game = new TicTacToe2(this.printer, this.interaction);
                break;
            case GOMUKO:
                this.game = new TicTacToe3(this.printer, this.interaction);
                break;
        }
        return GameState.PLAYING;
    }
    private GameState play(){
        return this.game.playGame(this.stateMachine);
    }
    private GameState askPlayAgain(boolean function){
        String newGame = correctNewGameEntry(function);
        if (newGame.equalsIgnoreCase("y")){
            return GameState.INSTANTIATEGAME;
        }
        return askOtherGame();
    }
    private GameState askOtherGame(){
        String otherGame = correctNewGameEntry(false);
        if (otherGame.equalsIgnoreCase("y")){
            return GameState.CHOOSEGAME;
        }
        return GameState.EXIT;
    }
    private String correctNewGameEntry(boolean function){
        boolean correctEntry = false;
        String newGameChoice;
        do {
            displayNewGameOrOtherGame(function);
            newGameChoice = this.interaction.getPlayAgainChoice();
            if (newGameChoice.equalsIgnoreCase("Y") || newGameChoice.equalsIgnoreCase("N")) {
                correctEntry = true;
            }
        } while (!correctEntry);
        return newGameChoice;
    }
    private void displayNewGameOrOtherGame(boolean function){
        if (function){
            this.printer.displayAskNewGame();
        } else {
            this.printer.displayAskOtherGame();
        }
    }
}
