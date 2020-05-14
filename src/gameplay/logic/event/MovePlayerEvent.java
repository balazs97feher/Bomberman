package gameplay.logic.event;

import gameplay.grid.Direction;

/**
* An event to indicate a request to move a player on the grid
*/
public class MovePlayerEvent implements GameEvent {
    private String playerName;
    private Direction direction;

    public MovePlayerEvent(String name, Direction dir){
        playerName = name;
        direction = dir;
    }

    @Override
    public GameEventType getEventType() {
        return GameEventType.MOVE_PLAYER;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "MovePlayerEvent{" +
            "playerName='" + playerName + '\'' +
            ", direction=" + direction +
            '}';
    }
}
