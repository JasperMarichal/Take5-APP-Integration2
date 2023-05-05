package model;

public class DmgCalculator {

    public void takeDmgHuman(int bullPoints, PlayingTable playingTable){
     int cpnow=playingTable.getPlayers()[0].getCounterPoints();
        playingTable.getPlayers()[0].setCounterPoints((cpnow- bullPoints));
     checkWin(playingTable.getPlayers()[0],playingTable.getPlayers()[1]);
    }
    public void takeDmgAI(int bullPoints, PlayingTable playingTable){
        int cpnow=playingTable.getPlayers()[1].getCounterPoints();
        playingTable.getPlayers()[1].setCounterPoints((cpnow- bullPoints));
        checkWin(playingTable.getPlayers()[1],playingTable.getPlayers()[1]);
    }
    public int checkWin(Player HumanPlayer, Player AI) {
        int hpLifePoints = HumanPlayer.getCounterPoints();
        int AILifePoints = AI.getCounterPoints();
        if (hpLifePoints==0){
            return 1;
        }
        if (AILifePoints==0){
            return 2;
        }
        return 0;
    }
}
