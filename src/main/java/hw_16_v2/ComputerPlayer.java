package hw_16_v2;

import hw_16.Player;

public class ComputerPlayer extends IPlayer{

    public ComputerPlayer(int id){
        super(id);
    }

    @Override
    public void makeChoice() {
        switch ((int)(Math.random()*3)){
            case 0:
                shape = Shape.SCISSORS;
                break;
            case 1:
                shape = Shape.STONE;
                break;
            case 2:
                shape = Shape.PAPER;
                break;
            default:
                shape = Shape.UNDEFINED;
                break;
        }
    }

    @Override
    public String toString() {
        return "Компьютер #" + id + " Cчет: " + score;
    }
}
