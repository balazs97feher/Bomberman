package GUI;

import gameplay.LoggerMan;
import gameplay.logic.Game;
import gameplay.logic.event.*;
import javafx.application.Platform;

import java.io.IOException;
import java.util.logging.Level;

public class Renderer implements Runnable{
    private Game game;
    private GUI gui;
    private boolean online;

    public Renderer(Game ga, GUI gu){
        game = ga;
        gui = gu;
        online = false;
    }

    @Override
    public void run() {
        if(!online) renderOfflineGame();
    }

    private void renderOfflineGame(){
        while(game.isRunning()){
            GameEvent nextEvent = game.pollEventPump();
            if(nextEvent != null){
                switch (nextEvent.getEventType()){
                    case MONSTER_MOVED:
                        MonsterMovedEvent e1 = (MonsterMovedEvent)nextEvent;
                        Platform.runLater(() -> gui.movemonster(e1.getMonsterId(),e1.getDirection()));
                        break;
                    case MONSTER_KILLED:
                        MonsterKilledEvent e6 = (MonsterKilledEvent)nextEvent;
                        Platform.runLater(() -> gui.removemonster(e6.getMonsterId()));
                        break;
                    case PLAYER_MOVED:
                        PlayerMovedEvent e2 = (PlayerMovedEvent)nextEvent;
                        Platform.runLater(() -> gui.moveplayer(e2.getPlayerId(),e2.getDirection()));
                        break;
                    case PLAYER_KILLED:
                        PlayerKilledEvent e5 = (PlayerKilledEvent)nextEvent;
                        Platform.runLater(() -> gui.removeplayer(e5.getplayerId()));
                        break;
                    case BOMB_PLACED:
                        BombPlacedEvent e3 = (BombPlacedEvent)nextEvent;
                        Platform.runLater(() -> gui.addbomb(e3.getPosition(),e3.getBombId()));
                        break;
                    case BOMB_DETONATED:
                        BombDetonatedEvent e4 = (BombDetonatedEvent)nextEvent;
                        Platform.runLater(() -> gui.removebomb(e4.getBombId()));
                        break;
                }
            }
        }
        Platform.runLater(() -> {
            try {
                gui.backtomain();
            } catch (IOException e) {
                e.printStackTrace();
                LoggerMan.log(Level.SEVERE, "Main menu couldn't be loaded.");
            }
        });
    }

}
