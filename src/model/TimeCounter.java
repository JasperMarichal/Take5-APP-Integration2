package model;

import java.util.Timer;
import java.util.TimerTask;

public class TimeCounter extends java.util.Timer {
    private Timer timer;
    private TimerTask timerTask;
    int secondsLeft= 150;



    public TimeCounter(PlayingTable playingTable) {
        timer = new Timer();
        timerTask = new TimerTask() {

            @Override
            public void run() {
                System.out.println(secondsLeft + "seconds left ");

                secondsLeft--;
                if (secondsLeft <0){
                    playingTable.setTimerCounter(1);

                }
            }


        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
            }

    public void update(){
                secondsLeft=150;

    }

    public int getSecondsLeft() {
        return secondsLeft;
    }



}
