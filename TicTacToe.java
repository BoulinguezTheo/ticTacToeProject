public class TicTacToe {
    Player player1;
    Player player2;
    private int size = 3;
    private int sizeHeight;
    private int sizeLength;
    private int turns = 0;
    private boolean endGame;
    private int[]   playersInput;
    private String [][] boardArray;
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
            turns = player1.getTurns();
            display();
            isMoveValid();
            setOwner(playersInput);
            if(isWinner()){
                endGame = true;
            }
            if (turns == 9){
                endGame = true;
            }

        } while(!endGame);
    }

    public boolean isWinner(){
        int columnInput = playersInput[0];
        int lineInput = playersInput[1];
            //fonction détermine win
        if (columnInput == sizeHeight && lineInput == sizeLength){
            //check diag
            for(int i = 1; i < this.size; i++){
                if (this.boardArray[columnInput + i][lineInput + i] == this.player1.getSymbol()){
                    continue;
                }
                if (this.boardArray[columnInput][lineInput + i] == this.player1.getSymbol()){
                    continue;
                }
                if (this.boardArray[columnInput + i][lineInput] == this.player1.getSymbol()){
                }
            }
        }


        return false;
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



}
