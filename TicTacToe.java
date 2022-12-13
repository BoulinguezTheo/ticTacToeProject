import java.util.ArrayList;
import java.util.Arrays;

public class TicTacToe {
    Player player1;
    Player player2;
    private final int winCondition = 3;
    private int size = 3;
    private int sizeHeight;
    private int sizeLength;
    private boolean endGame;
    private int[]   playersInput;
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

    public String[][] getBoard(){
        return this.boardArray;
    }



    public TicTacToe() {   // Constructeur --> donner un valeur aux attributs
        board = new Cell();
        sizeHeight = 3;
        sizeLength = 3;
        boardArray = new String[sizeHeight][sizeLength];
        setupArray();
        player1 = new Player();
        player2 = new Player();
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

    public boolean isWinner(){
        String symbolPlayed = player1.getSymbol();
        if( symbolPlayed == "X"){
            if (addInputToArrayAndCheckIfGameWonLineColumn(linesArrayX, 1)
                || addInputToArrayAndCheckIfGameWonLineColumn(columnArrayX, 0)
                || addInputToArrayAndCheckIfGameWonDiags(diagXMinusOneArrayX, -1)
                || addInputToArrayAndCheckIfGameWonDiags(diagXPlusOneArraysX, 1)){
                return true;
            } else {
                return false;}
        } else {
            System.out.println("Test is winner O"); //OK
            if (addInputToArrayAndCheckIfGameWonLineColumn(linesArrayO, 1)
                    || addInputToArrayAndCheckIfGameWonLineColumn(columnArrayO, 0)
                    || addInputToArrayAndCheckIfGameWonDiags(diagXMinusOneArrayO, (-1))
                    || addInputToArrayAndCheckIfGameWonDiags(diagXPlusOneArraysO, 1)){
                return true;
            } else {
                return false;}
        }
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


    private boolean addInputToArrayAndCheckIfGameWonLineColumn(ArrayList<ArrayList<int[]>> arrayToCheck, int coordinateToCheck){
        int x = playersInput[0];
        int y = playersInput[1];
        if (arrayToCheck.size() == 0){
            ArrayList<int[]> array = new ArrayList<int[]>();
            array.add(playersInput);
            arrayToCheck.add(array);
        } else {
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
        return false;
    }
    private boolean addInputToArrayAndCheckIfGameWonDiags(ArrayList<ArrayList<int[]>> arrayToCheck, int sign){

        int x = playersInput[0];
        int y = playersInput[1];
        if (arrayToCheck.size() == 0){
            ArrayList<int[]> array = new ArrayList<int[]>();
            array.add(playersInput);
            arrayToCheck.add(array);
        } else {
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
        return false;
    }






}
