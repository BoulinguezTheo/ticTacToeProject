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

public class AppController{

    protected final String resetSymbol = " ";
    protected ShowInterface printer;
     //re
    protected GameControllerInterface game;
    public UserInteractionInterface interaction;
    protected GameState stateMachine;


    
    public AppController(){
        this.printer = new ShowEn();
        this.interaction = new UserInteraction();
        this.stateMachine = GameState.INSTANTIATEGAME;
    }

    public void run(){
        String gameChoice = askGameChoice();
        while(stateMachine != GameState.EXIT){
            switch(stateMachine){
                case INSTANTIATEGAME -> this.stateMachine = initGame(gameChoice);
                case PLAYING -> this.stateMachine = play();
                case NEWGAME -> ;
                case EXIT -> ;
            }
        }
    }

    private String askGameChoice() {
        this.printer.displayGameChoice();
        String input = this.boardGame.getGameChoice();
        boolean correctInput = false;
        while (!correctInput) {
            if (input.equalsIgnoreCase("1") || input.equalsIgnoreCase("2") || input.equalsIgnoreCase("3")){
                correctInput = true;
            } else {
                this.printer.displayGameChoice();
                input = this.boardGame.getGameChoice();
            }
        }
        return input;
    }

    private GameState initGame(String pGameChoice){
        switch(pGameChoice){
            case "1":
                this.game = new TicTacToe();
                break;
            case "2":
                this.game = new TicTacToe2();
                break;
            case "3":
                this.game = new TicTacToe3();
                break;
        }
        return GameState.PLAYING;
    }

    private GameState play(){
        this.game.playGame(this.interaction, this.printer);
    }

//    public void play(GameControllerInterface pGame){
//        while(stateMachine != GameFunction.EXIT){
//            switch (stateMachine){
//                case INITGAME:
//                    stateMachine = this.initGameFunctions();
//                    break;
//                case PLAYGAME:
//                    stateMachine = this.playGame();
//                    break;
//                case ENDGAME:
//                    stateMachine = GameFunction.PLAYAGAIN;
//                    break;
//                case PLAYAGAIN:
//                    stateMachine = this.treatPlayAgainChoice();
//                    break;
//                case RESETBOARD:
//                    stateMachine = this.resetBoard(pGame.getSizeHeight(), pGame.getSizeLength());
//                    break;
//                case EXIT:
//                    break;
//            }
//        }
//        printer.displayExit();
//    }
////    public abstract void getValidMove();
//    public int[] getPlayersInput(){
//        return this.playersInput;
//    }


    public void resetCells(String pSymbolReset, int pSizeHeight, int pSizeLength){
        for (int i = 0; i < pSizeHeight; i++){
            for (int j = 0; j < pSizeLength; j++){
                int[] resetCell = {j, i};
                this.boardGame.setOwner(resetCell, pSymbolReset);
            }
        }
    }
//    public abstract boolean checkIfGameWonColumnAndLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck);
//    public abstract boolean checkIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign);
    public GameFunction treatPlayAgainChoice(){
        if (correctNewGameEntry().equalsIgnoreCase("y")){
            return GameFunction.RESETBOARD;
        }
        return GameFunction.EXIT;
    }
    public String correctNewGameEntry(){
        boolean correctEntry = false;
        String newGameChoice;
        do {
            this.printer.displayAskNewGame();
            newGameChoice = this.interaction.getPlayAgainChoice();
            if (newGameChoice.equalsIgnoreCase("Y") || newGameChoice.equalsIgnoreCase("N")) {
                correctEntry = true;
            }
        } while (!correctEntry);
        return newGameChoice;
    }



    public GameFunction resetBoard(int pHeight, int pLength){
        resetCells(this.resetSymbol, pHeight, pLength);
        this.boardGame.resetTurns();
        return GameFunction.INITGAME;
    }
}
