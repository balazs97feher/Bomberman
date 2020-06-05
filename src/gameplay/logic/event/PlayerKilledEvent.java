package gameplay.logic.event;

public class PlayerKilledEvent implements GameEvent {
    private int playerId;

    public PlayerKilledEvent(int id){
        playerId = id;
    }

    @Override
    public GameEventType getEventType() {
        return GameEventType.PLAYER_KILLED;
    }

    public int getMonsterId() {
        return playerId;
    }
}
