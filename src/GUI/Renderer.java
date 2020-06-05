package GUI;

import gameplay.logic.Game;
import gameplay.logic.event.GameEvent;
import gameplay.logic.event.MonsterMovedEvent;

public class Renderer implements Runnable {
    private Game game;
    private GUI gui;

    public Renderer(Game ga, GUI gu){
        game = ga;
        gui = gu;
    }

    @Override
    public void run() {
        while(game.isRunning()){
            GameEvent nextEvent = game.pollEventPump();
            if(nextEvent != null){
                switch (nextEvent.getEventType()){
                    case MONSTER_MOVED:
                        MonsterMovedEvent e1 = (MonsterMovedEvent)nextEvent;
                        gui.movemonster(e1.getMonsterId(),e1.getDirection());
                        break;
                    case MONSTER_KILLED:
                        break;
                    case MOVE_PLAYER:
                        break;
                    case PLAYER_MOVED:
                        break;
                    case PLAYER_KILLED:
                        break;
                    case PLACE_BOMB:
                        break;
                    case BOMB_PLACED:
                        break;
                    case BOMB_DETONATED:
                        break;
                }
            }
        }

    }
}
