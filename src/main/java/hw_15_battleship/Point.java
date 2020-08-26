package hw_15_battleship;

public class Point {
    //Состояния точки на поле
    public enum Status{
        SEA,
        EMPTY,
        SHIP_HIDDEN,
        SHIP_CRACKED,
    }
    Status status;
    //Как будет отображаться точка на поле
    String display;
    public Point(Status status){
        this.status = status;

        switch (status){
            case SEA:
            case SHIP_HIDDEN:
                display = "~";
                break;
            case EMPTY:
                display = "O";
                break;
            case SHIP_CRACKED:
                display = "X";
                break;
        }
    }
    //Обновление состояние точки
    public void updatePoint(){
        switch (status){
            case SEA:
            case SHIP_HIDDEN:
                display = "~";
                break;
            case EMPTY:
                display = "O";
                break;
            case SHIP_CRACKED:
                display = "X";
                break;
        }
    }
}
