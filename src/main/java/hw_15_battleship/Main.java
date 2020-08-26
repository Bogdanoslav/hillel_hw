package hw_15_battleship;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Scanner s = new Scanner(System.in);
        String answ = "";
        int tries = -1;
        int x, y;
        int width = 4;
        int height = 4;
        while(true){
            showMenu();
            answ = s.nextLine();
            if(answ.toLowerCase().equals("q")){
                break;
            }
            else if(answ.toLowerCase().equals("y")){
                s = new Scanner(System.in);
                while(tries<=0){
                    System.out.printf("Введите количество попыток(попытки > 0, врагов будет %d) => ", (width*height)/5);
                    while (!s.hasNextInt()){
                        System.out.print("Введите число => ");
                        s.next();
                    }
                    tries = s.nextInt();
                }
                BattleShip battleShip = new BattleShip(width,height, tries);
                while (battleShip.getTries()!=0){
                    s = new Scanner(System.in);
                    battleShip.showField();
                    System.out.print("x => ");
                    while (!s.hasNextInt()){
                        System.out.print("Некорректный ввод. Повторите x => ");
                        s.next();
                    }
                    x = s.nextInt();
                    System.out.print("y => ");
                    while (!s.hasNextInt()){
                        System.out.print("Некорректный ввод. Повторите y => ");
                        s.next();
                    }
                    y = s.nextInt();
                    battleShip.checkGuess(x,y);
                    if(battleShip.getEnemies_c()==0){
                        battleShip.showField();
                        System.out.println("Победа, все корабли подбиты.");
                        break;
                    }
                    else if (battleShip.getTries()==0){
                        battleShip.showField();
                        System.out.println("Поражение, кончились попытки.");
                    }
                }
            }
            else {
                continue;
            }
        }
    }
    public static void showMenu(){
        System.out.println("    Игра попади по кораблю");
        System.out.println("y - Начать игру");
        System.out.println("q - Закрыть игру");
    }
}
