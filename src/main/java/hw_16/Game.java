package hw_16;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static hw_16.Player.Shape.*;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] correct_shapes = {"б","к","н"};
        String answ = "";
        int numOfGames = 0;
        int humans = 0;
        int computers = 0;
        List<Player> players = new ArrayList<>();
        int i = 0;

        System.out.println("К/к - камень");
        System.out.println("Н/н ножницы");
        System.out.println("Б/б бумага");

        Scanner s_num = new Scanner(System.in);

        while(computers+humans<=1){
            System.out.println("\nКоличество игроков должно быть больше 1");
            do{
                System.out.print("Количество людей: ");
                while (!s_num.hasNextInt()){
                    System.out.print("Количество людей: ");
                    s_num.next();
                }
                humans = s_num.nextInt();
            } while (humans<0);

            do{
                System.out.print("Количество компьютеров: ");
                while (!s_num.hasNextInt()){
                    System.out.print("Количество компьютеров: ");
                    s_num.next();
                }
                computers = s_num.nextInt();
            } while (computers<0);

            do{
                System.out.print("Количество игр: ");
                while (!s_num.hasNextInt()){
                    System.out.print("Количество игр: ");
                    s_num.next();
                }
                numOfGames = s_num.nextInt();
            } while (numOfGames<0);
        }

        RoshamboSession rs = new RoshamboSession(computers, humans, numOfGames);
        //Добавление компьютеров
        for (i = 0; i < computers; i++) {
            rs.players.add(new Player(i, true));
        }
        humans+=i;
        //Добавление людей
        for (; i < humans; i++) {
            rs.players.add(new Player(i, false));
        }
        Scanner s_string = new Scanner(System.in);
        //Цикл раунда
        for (int j = 0; j < numOfGames; j++){
            answ = "";
            System.out.println("Прервать игру? (Q/q) / Любую строку чтобы продолжить");
            answ = s_string.nextLine();
            if(answ.toLowerCase().equals("q")){
                break;
            }
            players = rs.players;
            //Цикл определения победителя
            do {
                System.out.println();

                //Выбор компьютеров и людей
                for (Player p : players) {
                    if (p.isComputer()) {
                        p.computerChoice();
                    }
                    else {
                        String shape = "U";
                        do{
                            System.out.printf("Игрок номер %d выберите фигуру:  ", p.getId());
                            shape = s_string.nextLine();
                            System.out.println();
                        }while (Arrays.binarySearch(correct_shapes, shape)<0);
                        p.humanChoice(shape);
                    }
                }

                //Вывод игроков и компьютерв с их выборами
                showPlayers(players);

                //Убираем повторяющиейся фигуры
                List<Player.Shape> shapes = players.stream().distinct().map(s -> s.getShape()).collect(Collectors.toList());

                //Определяем выигрышную фигуру
                Player.Shape winShape = defineWinningShape(shapes);

                //Собираем всех игроков с выигрышной фигурой и отправляем в след. раунд
                if(!(winShape == UNDEFINED))
                    players = players.stream().filter(s -> s.getShape() == winShape).collect(Collectors.toList());

            }while(players.size()!=1 && players.size()!=0);

            //увеличиваем очки победителя раунда

            players.get(0).setScore(players.get(0).getScore()+1);

            System.out.print("Победитель раунда:  ");
            showPlayers(players);
        }

        //Вычисление игркоа с самым большим результатом
        Player absWinner = rs.players.get(0);
        for (i = 0; i < rs.players.size()-1; i++) {
            if(rs.players.get(i).getScore()<rs.players.get(i+1).getScore()){
                absWinner = rs.players.get(i+1);
            }
        }

        //Вывод игроков с таким же количеством очков
        System.out.println("Победители: ");
        for (Player p:
             rs.players) {
            if(p.getScore() == absWinner.getScore()){
                if(p.isComputer())
                    System.out.printf("Компьютер номер %4d со счетом %d" + "\n", p.getId(),p.getScore());
                else
                    System.out.printf("Игрок номер %8d со счетом %d" + "\n", p.getId(),p.getScore());
            }
        }
        //Запись результатов в файл
        try {
            File file = new File("KamsatriResults.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter) ;
            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("Дата yyyy.MM.dd 'Время' hh:mm:ss");
            printWriter.println("\n" + formatForDateNow.format(dateNow));
            printWriter.println("Победители:");
            for (Player p:
                    rs.players) {
                if(p.getScore() == absWinner.getScore()){
                    if(p.isComputer())
                        printWriter.printf("Компьютер номер %4d     со счетом %4d" + "\n", p.getId(),p.getScore());
                    else
                        printWriter.printf("Игрок     номер %4d     со счетом %4d" + "\n", p.getId(),p.getScore());
                }
            }
            printWriter.close();
        } catch (IOException e){
            System.out.println(e);
            System.out.println("Ошибка при записи результатов");
        }


    }
    public static Player.Shape defineWinningShape(List<Player.Shape> shapes){
        if(shapes.size() == 3 || shapes.size() == 1){
            return UNDEFINED;
        }
        else{
            if(shapes.contains(SCISSORS))
                return shapes.contains(PAPER)? SCISSORS: STONE;
            if(shapes.contains(STONE))
                return shapes.contains(SCISSORS)? STONE: Player.Shape.PAPER;
        }
        return UNDEFINED;
    }
    public static void showPlayers(List<Player> players){
        for (Player p:players) {
            if(p.isComputer())
                System.out.printf("Компьютер номер %4d со счетом %4d" + "\n", p.getId(),p.getScore());
            else{
                System.out.printf("Игрок     номер %4d со счетом %4d" + "\n", p.getId(),p.getScore());
            }
        }
        System.out.println();
    }

}
