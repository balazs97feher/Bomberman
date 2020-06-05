package gameplay.logic.event;

public class PlayerKilledEvent implements GameEvent {
    private int playerId;

    public PlayerKilledEvent(int id){
        playerId = id;
    }

    public int getplayerId() {
        return playerId;
    }

    @Override
    public GameEventType getEventType() {
        return GameEventType.PLAYER_KILLED;
    }

    @Override
    public String toString() {
        return "PlayerKilledEvent{" +
            "playerId = " + playerId +
            '}';
    }
}
