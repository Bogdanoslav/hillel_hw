package hw_15_battleship;

public class BattleShip {
    private int width;
    private int height;
    //Количество врагов на поле
    private int enemies_c;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getEnemies_c() {
        return enemies_c;
    }

    public void setEnemies_c(int enemies_c) {
        this.enemies_c = enemies_c;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    //Количество попыток
    private int tries;
    //Двумерный массив - поле
    private Point[][] field;

    //Создание игрового поля
    public BattleShip(int width, int height, int tries)  {
        this.width = width;
        this.height = height;
        this.tries = tries;
        field = new Point[height][width];
        enemies_c = (width*height)/5;
        makeField();

    }

    //Заполнение поля водой и расстановка кораблей
    public void makeField() {
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width;j++){
                field[i][j] = new Point(Point.Status.SEA);
            }
        }
        for(int i = 0; i < enemies_c; i++){
            int m = (int)(Math.random()*height);
            int n = (int)(Math.random()*width);
            while(field[m][n].status!= Point.Status.SEA){
                m = (int)(Math.random()*height);
                n = (int)(Math.random()*width);
            }
            field[m][n] = new Point(Point.Status.SHIP_HIDDEN);
        }
    }

    //Вывод поля на экран
    public void showField(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width;j++){
                System.out.print(field[i][j].display + "   ");
            }
            System.out.println();
        }
    }

    public void checkGuess(int x, int y){
        try{
            Point.Status status = field[y][x].status;
            switch (status){
                case SEA:
                    field[y][x].status = Point.Status.EMPTY;
                    field[y][x].updatePoint();
                    tries--;
                    break;
                case SHIP_HIDDEN:
                    field[y][x].status = Point.Status.SHIP_CRACKED;
                    field[y][x].updatePoint();
                    enemies_c--;
                    tries--;
                    break;
                default:
                    System.out.println("Эта точка уже проверена");
                    break;
            }
        }catch (Exception e){
            System.out.println("Слишком далеко");
        }
    }
}
