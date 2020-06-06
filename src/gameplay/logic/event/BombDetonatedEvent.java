package gameplay.logic.event;

public class BombDetonatedEvent implements GameEvent {
    private int bombId;

    public BombDetonatedEvent(int id){
        bombId = id;
    }

    public int getBombId() {
        return bombId;
    }

    @Override
    public GameEventType getEventType() {
        return GameEventType.BOMB_DETONATED;
    }

    @Override
    public String toString() {
        return "BombDetonatedEvent{" +
            "bombId = " + bombId +
            '}';
    }
}
