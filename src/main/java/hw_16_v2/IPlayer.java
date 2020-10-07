package hw_16_v2;

import java.util.Objects;

public abstract class IPlayer {
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
    String[] correct_shapes = {"б","к","н"};
    protected Shape shape;
    protected int id;
    protected int score;

    public IPlayer(){

    }
    public IPlayer(int id){
        this.id = id;
    }

    public abstract void makeChoice();

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        IPlayer player = (IPlayer) o;
        return shape == player.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape);
    }

    public void setScore(int score) {
        this.score = score;
    }
    public Shape getShape() { return shape; }
    public void setShape(Shape shape) { this.shape = shape; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
}
