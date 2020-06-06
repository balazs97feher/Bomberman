package GUI;

import gameplay.grid.Direction;
import gameplay.grid.Position;
import gameplay.logic.Game;
import gameplay.logic.event.BombPlacedEvent;
import gameplay.logic.event.GameEvent;
import gameplay.logic.event.MonsterMovedEvent;
import gameplay.logic.event.PlayerMovedEvent;
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
                        PlayerMovedEvent e2 = (PlayerMovedEvent)nextEvent;
                        Platform.runLater(() -> gui.moveplayer(e2.getPlayerId(),e2.getDirection()));
                        break;
                    case PLAYER_KILLED:
                        break;
                    case BOMB_PLACED:
                        BombPlacedEvent e3 = (BombPlacedEvent)nextEvent;
                        Platform.runLater(() -> gui.addbomb(e3.getPosition(),e3.getBombId()));
                        break;
                    case BOMB_DETONATED:
                        break;
                }
            }
            gui.window.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    switch(keyEvent.getCode()){
                        case UP: gui.moveplayer(0, Direction.NORTH); break;
                        case DOWN: gui.moveplayer(0, Direction.SOUTH); break;
                        case RIGHT: gui.moveplayer(0, Direction.EAST); break;
                        case LEFT: gui.moveplayer(0, Direction.WEST); break;
                        case SPACE: gui.addbomb(new Position(1, 1), 5); break;
                    }
                }
            });

        }
    }
}
