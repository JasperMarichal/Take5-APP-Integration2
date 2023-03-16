package model;

public class DmgCalculator {
    int lifePoints;

    public void takeDmg(int bullPoints, Player humanPlayer){
     int cpnow=humanPlayer.getCounterPoints();
     humanPlayer.setCounterPoints((cpnow- bullPoints));
    }
}
