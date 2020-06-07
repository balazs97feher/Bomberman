package gameplay.logic.event;

import static gameplay.logic.event.GameEventType.LEVEL_FINISHED;

public class LevelFinishedEvent implements GameEvent {
    private int levelNo;

    public LevelFinishedEvent(int no){
        levelNo = no;
    }

    public int getLevelNo() {
        return levelNo;
    }

    @Override
    public GameEventType getEventType() {
        return LEVEL_FINISHED;
    }

    @Override
    public String toString() {
        return "LevelFinishedEvent{" +
            "levelNo = " + levelNo +
            '}';
    }
}
