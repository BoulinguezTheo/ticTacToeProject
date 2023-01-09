package src.model;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GameSerialization implements Persistance {

    @Override
    public void saveStateBoard(BoardInterface pBoard) {
        try{
            //Creating FileOutputStream object.
            FileOutputStream fos = new FileOutputStream("C:\\Users\\theo.boulinguez\\Documents\\GitHub\\ticTacToeProject");
            //Creating ObjectOutputStream object.
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //write object.
            oos.writeObject(pBoard);
            //close streams.
            oos.close();
            fos.close();

            System.out.println("Board data is saved in " +
                    "C:\\Users\\theo.boulinguez\\Documents\\GitHub\\ticTacToeProject");
        }catch(Exception e){
            System.out.println(e);}
    }

    @Override
    public void savePlayerTurn(Player pActivePlayer) {
       try{
           FileOutputStream fos = new FileOutputStream("C:\\Users\\theo.boulinguez\\Documents\\GitHub\\ticTacToeProject");
           ObjectOutputStream oos = new ObjectOutputStream(fos);
           oos.writeObject(pActivePlayer);

           oos.close();
           fos.close();

           System.out.println("Player data is saved in " +
                   "C:\\Users\\theo.boulinguez\\Documents\\GitHub\\ticTacToeProject");
       }catch (Exception e){
           System.out.println(e);
       }
    }
}
