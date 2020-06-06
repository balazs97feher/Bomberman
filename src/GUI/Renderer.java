package GUI;

import gameplay.logic.Game;
import gameplay.logic.event.GameEvent;
import gameplay.logic.event.MonsterMovedEvent;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Renderer implements Runnable{
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
                        Platform.runLater(() -> gui.movemonster(e1.getMonsterId(),e1.getDirection()));
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
            gui.window.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    switch(keyEvent.getCode()){
                        case UP: System.out.println("Up pressed"); break;
                        case DOWN: System.out.println("Down pressed"); break;
                        case RIGHT: System.out.println("Right pressed"); break;
                        case LEFT: System.out.println("Left pressed"); break;
                        case SPACE: System.out.println("Space pressed"); break;
                    }
                }
            });

        }
    }
}
