import java.io.Serializable;



//Fontos h Serializable legyen az osztaly
public class TestPlayerMoved implements Serializable{

    private int playerId;
    private int previousPosition;
    private int newPosition;

    public void PlayerMovedEvent(int id, int previousPos, int newPos){
        playerId = id;
        previousPosition = previousPos;
        newPosition = newPos;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getPreviousPosition() {
        return previousPosition;
    }

    public int getNewPosition() {
        return newPosition;
    }



}
