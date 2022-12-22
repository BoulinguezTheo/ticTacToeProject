package src;

import java.util.ArrayList;

public abstract class BoardGame {
    Cell[][] boardGame;
    Player player1;
    Player player2;
    UserInteraction interaction;
    View printer;
    int turns;

    public BoardGame(){
        this.interaction = new UserInteraction();
        this.printer = new View();
        createPlayers();
        turns = 0;
    }

    private void createPlayers(){
        this.player1 = this.interaction.setupPlayers("1st", "X");
        this.player2 = this.interaction.setupPlayers("2st", "O");
    }

    protected abstract void play();
    protected abstract void playGame();

    public boolean processInputColumnLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck, int[] inputCoordinates){
        if (arrayToCheck.size() == 0){
            createNewArrayOfCoordinates(arrayToCheck, inputCoordinates);
            return false;
        } else {
            return checkIfGameWonColumnAndLines(arrayToCheck,coordinateToCheck);
        }
    }

    public boolean processInputDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign, int[] inputCoordinates){
        if (arrayToCheck.size() == 0){
            createNewArrayOfCoordinates(arrayToCheck, inputCoordinates);
            return false;
        } else {
            return checkIfGameWonDiags(arrayToCheck, sign);
        }
    }
    
    protected abstract boolean checkIfGameWonColumnAndLines();
    protected abstract boolean checkIfGameWonDiags();

    private void createNewArrayOfCoordinates(ArrayList<ArrayList<int[]>> arrayToCheck, int[] inputCoordinates){
        ArrayList<int[]> array = new ArrayList<int[]>();
        array.add(inputCoordinates);
        arrayToCheck.add(array);
    }

    

}
