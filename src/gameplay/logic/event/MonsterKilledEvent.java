package gameplay.logic.event;

public class MonsterKilledEvent implements GameEvent {
    private int monsterId;

    public MonsterKilledEvent(int id){
        monsterId = id;
    }

    @Override
    public GameEventType getEventType() {
        return GameEventType.MONSTER_KILLED;
    }

    public int getMonsterId() {
        return monsterId;
    }
}
