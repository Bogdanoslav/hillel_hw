package hw_16_v2;

import java.util.Arrays;
import java.util.Scanner;

public class HumanPlayer extends IPlayer{
    public static Scanner hPlayerScanner = new Scanner(System.in);

    public HumanPlayer(int id){
        super(id);
    }

    @Override
    public void makeChoice() {

        String ch;
        do{
            System.out.print("Игрок #" + id + " сделайте выбор: ");
            ch = hPlayerScanner.nextLine().toLowerCase();
            System.out.println();
        }while (Arrays.binarySearch(correct_shapes, ch)<0);
        switch (ch.toLowerCase()){
            case "н":
                shape = Shape.SCISSORS;
                break;
            case "к":
                shape = Shape.STONE;
                break;
            case "б":
                shape = Shape.PAPER;
                break;
            default:
                shape = Shape.UNDEFINED;
                break;
        }
    }

    @Override
    public String toString() {
        return "Игрок     #" + id + " Cчет: " + score;
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
