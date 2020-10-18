package hw_16_v2;

import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HumanPlayer extends IPlayer{
    public static Scanner hPlayerScanner = new Scanner(System.in);

    public HumanPlayer(int id, ResourceBundle rb){
        super(id, rb);
    }

    @Override
    public void makeChoice() {

        String ch;
        do{
            System.out.print(resourceBundle.getString("player") + "#" + id + " " +  resourceBundle.getString("input_makeChoice"));
            ch = hPlayerScanner.nextLine().toLowerCase();
            System.out.println();
        }while (!Arrays.asList(correct_shapes).contains(ch));

            if(ch.equals(correct_shapes[0]))
                shape = Shape.ROCK;
            else if(ch.equals(correct_shapes[1]))
                shape = Shape.SCISSORS;
            else if(ch.equals(correct_shapes[2]))
                shape = Shape.PAPER;
            else
                shape = Shape.UNDEFINED;
    }

    @Override
    public String toString() {
        return resourceBundle.getString("player") + " #" + id + " " + resourceBundle.getString("score") + score;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

