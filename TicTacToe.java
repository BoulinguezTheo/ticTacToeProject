import java.util.ArrayList;
import java.util.Arrays;

public class TicTacToe {
    Player player1;
    private final int winCondition = 3;
    private int sizeHeight;
    private int sizeLength;
    private boolean endGame;
    private int[] playersInput;
    private String [][] boardArray;

    private ArrayList<ArrayList<int[]>> linesArrayX = new ArrayList<ArrayList<int[]>>();
    private ArrayList<ArrayList<int[]>> columnArrayX = new ArrayList<ArrayList<int[]>>();
    private ArrayList<ArrayList<int[]>> diagXMinusOneArrayX = new ArrayList<ArrayList<int[]>>();
    private ArrayList<ArrayList<int[]>> diagXPlusOneArraysX = new ArrayList<ArrayList<int[]>>();
    private ArrayList<ArrayList<int[]>> linesArrayO = new ArrayList<ArrayList<int[]>>();
    private ArrayList<ArrayList<int[]>> columnArrayO = new ArrayList<ArrayList<int[]>>();
    private ArrayList<ArrayList<int[]>> diagXMinusOneArrayO = new ArrayList<ArrayList<int[]>>();
    private ArrayList<ArrayList<int[]>> diagXPlusOneArraysO = new ArrayList<ArrayList<int[]>>();
    Cell board;


    public TicTacToe() {   // Constructeur --> donner un valeur aux attributs
        board = new Cell();
        sizeHeight = 3;
        sizeLength = 3;
        boardArray = new String[sizeHeight][sizeLength];
        setupArray();
        player1 = new Player();
        play();
    }
    public void play(){
        do {
            int turns = player1.getTurns();
            display();
            isMoveValid();
            setOwner(playersInput);
            if(isWinner()){
                endGame = true;
            }
            if (turns == 8){
                endGame = true;
            }

        } while(!endGame);
    }
    public void isMoveValid(){
        do {
            playersInput = this.player1.getMoveFromPlayer();
        } while (isBoxFilled(playersInput));
    }
    public boolean isBoxFilled(int[] input){
        int inputColumn = input[0];
        int inputLine = input[1];
        if(this.boardArray[inputColumn][inputLine] == " "){
            return false;
        } else {
            System.out.println("Box already filled, please try again");
            return true;
        }
    }
    public void setOwner(int[] input){
        boardArray[input[0]][input[1]] = this.player1.getRepresentation();
    }
    public void display() {
        for (int j = 0; j < this.sizeHeight; j++) {
            System.out.println("-------------");
            for (int i = 0; i < this.sizeLength; i++) {
                System.out.print(this.board.getRepresentation() + this.boardArray[i][j] + " ");
            }
            System.out.print("|");
            System.out.println("");
        }
        System.out.println("-------------");
    }
    public void setupArray(){
        for (int i = 0; i < sizeHeight; i++){
            for (int j = 0; j < sizeLength; j++){
                boardArray[i][j] = " ";
            }
        }
    }
    public boolean isWinner(){
        String symbolPlayed = player1.getSymbol();
        if (treatInputColumnLines(symbolPlayed.equals("X") ? linesArrayX : linesArrayO, 1)
                || treatInputColumnLines(symbolPlayed.equals("X") ? columnArrayX : columnArrayO, 0)
                || treatInputDiags(symbolPlayed.equals("X") ? diagXMinusOneArrayX : diagXMinusOneArrayO, -1)
                || treatInputDiags(symbolPlayed.equals("X") ? diagXPlusOneArraysX : diagXPlusOneArraysO, 1)){
            return true;
        } else {
            return false;
        }
    }
    private boolean treatInputColumnLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck){
        if (arrayToCheck.size() == 0){
            createNewArrayOfCoordinates(arrayToCheck);
            return false;
        } else {
            return checkIfGameWonColumnAndLines(arrayToCheck,coordinateToCheck);
        }
    }
    private boolean treatInputDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign){
        if (arrayToCheck.size() == 0){
            createNewArrayOfCoordinates(arrayToCheck);
            return false;
        } else {
            return checkIfGameWonDiags(arrayToCheck, sign);
        }
    }
    private boolean checkIfGameWonColumnAndLines(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck){
        for(ArrayList<int[]> array : arrayToCheck){
            int[] getTuple = array.get(0);
            int coordinateToFit = getTuple[coordinateToCheck]; //colonne = [1] & ligne = [0]
            if(playersInput[coordinateToCheck] == coordinateToFit){
                array.add(playersInput);
            }
            if (array.size() == this.winCondition){
                return true;
            }
        }
        return false;
    }
    private boolean checkIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign){
        int x = playersInput[0];
        int y = playersInput[1];
        for(ArrayList<int[]> array : arrayToCheck){
            int[] getTuple = array.get(0);
            int coordinateToFitX = (x - getTuple[0]);
            int coordinateToFitY = sign * (y - getTuple[1]);
            if(coordinateToFitX == coordinateToFitY){
                array.add(playersInput);
            }
            if (array.size() == this.winCondition){
                return true;
            }
        }
        return false;
    }
    private void createNewArrayOfCoordinates(ArrayList<ArrayList<int[]>> arrayToCheck){
        ArrayList<int[]> array = new ArrayList<int[]>();
        array.add(playersInput);
        arrayToCheck.add(array);
    }
}
