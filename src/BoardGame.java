package src;

public abstract class BoardGame {
    Cell[][] boardGame;
    Player player1;
    Player player2;
    UserInteraction interaction;
    int turns;

    public BoardGame(){
        interaction = new UserInteraction();
        createPlayers();
        turns = 0;
    }

    private void createPlayers(){
        this.player1 = this.interaction.setupPlayers("1st", "X");
        this.player2 = this.interaction.setupPlayers("2st", "O");
    }

    protected abstract void play();
    protected abstract void playGame();

    

}
