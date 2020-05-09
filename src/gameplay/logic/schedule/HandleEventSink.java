package gameplay.logic.schedule;

import gameplay.logic.Controller;

import java.util.TimerTask;

public class HandleEventSink extends TimerTask {
    private Controller controller;

    public HandleEventSink(Controller c){
        controller = c;
    }

    private void notifyController(){
        controller.handleEventSink();
    }

    @Override
    public void run() {
        notifyController();
    }
}
