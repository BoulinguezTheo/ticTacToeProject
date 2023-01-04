package src.controller;


import src.model.BoardGame;
import src.model.Cell;
import src.model.GameInterface;
import src.model.Player;
import src.vue.ShowInterface;
import src.vue.UserInteraction;
import src.vue.ShowEn;
import src.vue.UserInteractionInterface;

import java.util.ArrayList;
import java.util.function.Function;

public class AppController{

    private final String ticTacToe = "1";
    private final String power4 = "2";
    private final String gomuko = "3";
    protected ShowInterface printer;
    protected GameControllerInterface game;
    public UserInteractionInterface interaction;
    protected GameState stateMachine;


    
    public AppController(){
        this.printer = new ShowEn();
        this.interaction = new UserInteraction();
        this.stateMachine = GameState.CHOOSEGAME;
    }

    public void run(){
        String gameChoice = "1";
        while(stateMachine != GameState.EXIT){
            switch(stateMachine){
                case CHOOSEGAME ->  {gameChoice = askGameChoice();
                                    this.stateMachine = GameState.INSTANTIATEGAME;}
                case INSTANTIATEGAME -> this.stateMachine = initGame(gameChoice);
                case PLAYING -> this.stateMachine = play();
                case NEWGAME -> this.stateMachine = askPlayAgain(true);
                case EXIT -> System.out.println("");
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
            case ticTacToe:
                this.game = new TicTacToe(this.printer, this.interaction);
                break;
            case power4:
                this.game = new TicTacToe2(this.printer, this.interaction);
                break;
            case gomuko:
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
    public GameState askOtherGame(){
        String otherGame = correctNewGameEntry(false);
        if (otherGame.equalsIgnoreCase("y")){
            return GameState.CHOOSEGAME;
        }
        return GameState.EXIT;
    }
    public String correctNewGameEntry(boolean function){
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
        if (function == true){
            this.printer.displayAskNewGame();
        } else {
            this.printer.displayAskOtherGame();
        }
    }
}
