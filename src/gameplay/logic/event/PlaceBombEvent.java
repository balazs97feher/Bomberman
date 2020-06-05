package gameplay.logic.event;

/**
 * An event to indicate a request to place a bomb by a player on the grid
 */
public class PlaceBombEvent implements GameEvent {
    private String playerName;

    public PlaceBombEvent(String name){
        playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public GameEventType getEventType() {
        return GameEventType.PLACE_BOMB;
    }

    @Override
    public String toString() {
        return "PlaceBombEvent{" +
            "playerName = " + playerName + '\'' +
            '}';
    }
}
