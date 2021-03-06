package hw_16_v2;

import hw_16.Player;

import java.util.ArrayList;
import java.util.List;

public class RoshamboSession_v2 {
    private int computers;
    private int humans;
    private int numOfGames;
    public List<IPlayer> players = new ArrayList<>();


    public RoshamboSession_v2(int computers, int humans, int numOfGames) {
        this.computers = computers;
        this.humans = humans;
        this.numOfGames = numOfGames;
    }



    public int getComputers() {
        return computers;
    }

    public void setComputers(int computers) {
        this.computers = computers;
    }

    public int getHumans() {
        return humans;
    }

    public void setHumans(int humans) {
        this.humans = humans;
    }

    public int getNumOfGames() {
        return numOfGames;
    }

    public void setNumOfGames(int numOfGames) {
        this.numOfGames = numOfGames;
    }

}
