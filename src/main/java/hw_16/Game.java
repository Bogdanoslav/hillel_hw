package hw_16;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static hw_16.Player.Shape.*;

public class Game {
    private static final Logger log =  LoggerFactory.getLogger(Game.class);
    public static void main(String[] args) {
        //============LOG================
        log.info("Пользователь запустил игру");
        //============ENDLOG================
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
        //Задание пользователем кол-ва игроков
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
        log.info("Количество выбранных игр: {}", numOfGames);

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

        int gamesLeft = numOfGames;
        int gamesPlayed = 0;

        //Цикл раунда
        for (int j = 0; j < numOfGames; j++){


            //Вопрос о прерывании игры каждый раунд
            System.out.print("Прервать игру? (Q/q) / Любую строку чтобы продолжить: ");
            answ = s_string.nextLine();
            if(answ.toLowerCase().equals("q"))
                break;

            //Список игроков для изменения
            players = new ArrayList<>(rs.players);
            //Цикл определения победителя
            do {
                System.out.println();
                //Выбор компьютеров
                for (Player p : players) {
                    if (p.isComputer()) {
                        p.computerChoice();
                    }
                }
                //Выбор людей
                for(Player p: players){
                    if(!p.isComputer()){
                        String shape;
                        do{
                            System.out.printf("Игрок номер %d выберите фигуру:  ", p.getId());
                            shape = s_string.nextLine().toLowerCase();
                            System.out.println();
                        }while (Arrays.binarySearch(correct_shapes, shape)<0);
                        p.humanChoice(shape);
                    }
                }

                //Вывод игроков и компьютерв с их выборами
                System.out.println("Игроки и выкинутые ими фигуры");
                showPlayersFigures(players);

                //============LOG================
                for(Player p: players) {
                    if(p.isComputer())
                        log.info("Computer chose {}", p.getShape().getName());
                    else{
                        log.info("Human chose {}", p.getShape().getName());
                    }
                }
                //============ENDLOG================

                //Убираем повторяющиейся фигуры
                List<Player.Shape> shapes = players.stream().distinct().map(s -> s.getShape()).collect(Collectors.toList());

                //Определяем выигрышную фигуру
                Player.Shape winShape = defineWinningShape(shapes);

                //Собираем всех игроков с выигрышной фигурой и отправляем в след. раунд
                if(winShape!=UNDEFINED)
                  players = players.stream().filter(s -> s.getShape() == winShape).collect(Collectors.toList());


                System.out.println("Оставшиеся игроки после бросания");
                showPlayersFigures(players);
                System.out.println("______________________________________");
            }while(players.size()>1);

            //увеличиваем очки победителя раунда
            for(Player p: players) {
                p.setScore(p.getScore() + 1);
            }

            System.out.print("\nПобедитель(и) раунда:  ");
            showPlayers(players);

            //============LOG================
            gamesPlayed++;
            gamesLeft--;
            log.info("Played games {}. Games left: {}", gamesPlayed, gamesLeft);
            //============ENDLOG================
        }

        //Вычисление самого высокого результата
        int highestScore = getHighestScore(players);

        //Вывод игроков с таким же количеством очков
        System.out.println("\n === Победители === ");
        ArrayList<Player> winners = new ArrayList<>();
        for (Player p: rs.players) {
            if(p.getScore() == highestScore){
                winners.add(p);
            }
        }
        showPlayers(winners);

        //Запись результатов в файл
        try {
            File file = new File("KamsatriResults.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter) ;
            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("Дата yyyy.MM.dd 'Время' hh:mm:ss");
            printWriter.println("\n" + formatForDateNow.format(dateNow));
            printWriter.println("Победители:");
            for (Player p: winners) {
                    printWriter.println(p + "\n");
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

    public static void showPlayersFigures(List<Player> players){
        for (Player p:players) {
            System.out.println(p + ".  Фигура = " + p.getShape().getName());
        }
    }
    public static void showPlayers(List<Player> players){
        for (Player p:players) {
            System.out.println(p);
        }
    }

    public static int getHighestScore(List<Player> players){
        int highestScore = -1;
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getScore()>highestScore){
                highestScore = players.get(i).getScore();
            }
        }
        return highestScore;
    }

}
