package hw_16_v2;

import java.util.ResourceBundle;

public class ComputerPlayer extends IPlayer{

    public ComputerPlayer(int id, ResourceBundle rb){
        super(id, rb);
    }

    @Override
    public void makeChoice() {
        switch ((int)(Math.random()*3)){
            case 0:
                shape = Shape.SCISSORS;
                break;
            case 1:
                shape = Shape.ROCK;
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
        return resourceBundle.getString("computer") + "#" + id + " " + resourceBundle.getString("score") + score;
    }
}
