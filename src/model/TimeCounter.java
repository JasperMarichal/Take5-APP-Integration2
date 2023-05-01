package model;

import java.util.Timer;
import java.util.TimerTask;

public class TimeCounter extends java.util.Timer {
    private Timer timer;
    private TimerTask timerTask;

    public TimeCounter(PlayingTable playingTable) {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Test za timera ");
                playingTable.setTimerCounter(1);
            }


        };
        timer.schedule(timerTask, 120000);
            }

    public void update(){
        timer.cancel();
        timer = new Timer();
        timer.schedule(timerTask, 120000);
    }
}
