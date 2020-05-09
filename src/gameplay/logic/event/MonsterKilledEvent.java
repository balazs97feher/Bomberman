package gameplay.logic.event;

public class MonsterKilledEvent implements GameEvent {
    public int monsterId;

    public MonsterKilledEvent(int id){
        monsterId = id;
    }

    @Override
    public GameEventType getEventType() {
        return GameEventType.MONSTER_KILLED;
    }
}
