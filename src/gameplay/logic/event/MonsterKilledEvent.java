package gameplay.logic.event;

public class MonsterKilledEvent implements GameEvent {
    private int monsterId;

    public MonsterKilledEvent(int id){
        monsterId = id;
    }

    public int getMonsterId() {
        return monsterId;
    }

    @Override
    public GameEventType getEventType() {
        return GameEventType.MONSTER_KILLED;
    }

    @Override
    public String toString() {
        return "MonsterKilledEvent{" +
            "monsterId = " + monsterId +
            '}';
    }
}
