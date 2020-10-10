package hw_16;



import org.apache.log4j.Logger;

import java.util.Objects;

public class Player {
    public enum Shape {
        SCISSORS("Ножницы"),
        STONE("Камень"),
        PAPER("Бумага"),
        UNDEFINED("Неопределено");
        String name;
        Shape(String name) {
            this.name = name;
        }
        public String getName(){ return name;}
    }
    protected Shape shape;
    protected int id;
    protected int score;
    String choice;



    boolean computer;

    public Player(int id, boolean computer){
        this.id = id;
        this.computer = computer;
        choice = "";
    }

    public void computerChoice(){
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
        }
    }
    public void humanChoice(String ch){
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

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public Shape getShape() {
        return shape;
    }
    public void setShape(Shape shape) { this.shape = shape; }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isComputer() { return computer; }
    public void setComputer(boolean computer) { this.computer = computer; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return shape == player.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape);
    }

    @Override
    public String toString() {
        if(computer)
            return "Компьютер #" + id + ", счет: " + score;
        else
            return "Человек #" + id + ", счет: " + score;
    }
}
