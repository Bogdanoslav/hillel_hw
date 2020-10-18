package hw_16_v2;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public abstract class IPlayer {
    static ResourceBundle resourceBundle;
    public enum Shape {
        SCISSORS,
        ROCK,
        PAPER,
        UNDEFINED
    }
    String[] correct_shapes;
    protected Shape shape;
    protected int id;
    protected int score;

    public IPlayer(){

    }
    public IPlayer(int id, ResourceBundle rs){
        this.id = id;
        resourceBundle = rs;
         correct_shapes = new String[]{rs.getString("input_rock"),rs.getString("input_scissors"),rs.getString("input_paper")};
    }

    public abstract void makeChoice();

    public int getScore() {
        return score;
    }

    public String getShapeName(){
        Shape shape = this.getShape();
        switch (shape){
            case ROCK:
                return resourceBundle.getString("shape_rock");
            case SCISSORS:
                return  resourceBundle.getString("shape_scissors");
            case PAPER:
                return  resourceBundle.getString("shape_paper");

        }
        return  resourceBundle.getString("shape_undefined");
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
