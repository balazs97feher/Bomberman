package gameplay.logic.event;

public class BombDetonatedEvent implements GameEvent {
    private int bombId;

    public BombDetonatedEvent(int id){
        bombId = id;
    }

    @Override
    public GameEventType getEventType() {
        return GameEventType.BOMB_DETONATED;
    }

    public int getMonsterId() {
        return bombId;
    }
}
