package hw_16_v2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static hw_16_v2.IPlayer.Shape.*;

public class Game_v2 {
    private static final Logger log =  LoggerFactory.getLogger(Game_v2.class);
    public static void main(String[] args) {
        //============LOG================
        log.info("\nПользователь запустил игру");
        //============ENDLOG================
        String answer;
        int numOfGames = 0;
        int humans = 0;
        int computers = 0;
        List<IPlayer> players = new ArrayList<>();


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

        RoshamboSession_v2 rs = new RoshamboSession_v2(computers, humans, numOfGames);
        //Добавление компьютеров
        int i;
        for (i = 0; i < computers; i++) {
            rs.players.add(new ComputerPlayer(i));
        }
        humans+=i;
        //Добавление людей
        for (; i < humans; i++) {
            rs.players.add(new HumanPlayer(i));
        }
        Scanner s_string = new Scanner(System.in);

        //Logger vars
        int gamesLeft = numOfGames;
        int gamesPlayed = 0;

        //Цикл раунда
        for (int j = 0; j < numOfGames; j++){


            //Вопрос о прерывании игры каждый раунд
            System.out.print("Прервать игру? (Q/q) / Любую строку чтобы продолжить: ");
            answer = s_string.nextLine();
            if(answer.toLowerCase().equals("q"))
                break;

            //Список игроков для изменения
            players = new ArrayList<>(rs.players);
            //Цикл определения победителя
            do {
                //Выбор игроков
                System.out.println();
                for(IPlayer p: players){
                    p.makeChoice();
                }
                //Вывод игроков и компьютерв с их выборами
                System.out.println("Игроки и выкинутые ими фигуры");
                System.out.println(showPlayersFigures(players));

                //============LOG================
                log.info("\n{}",showPlayersFigures(players));
                //============ENDLOG================

                //Убираем повторяющиейся фигуры
                List<IPlayer.Shape> shapes = players.stream().distinct().map(s -> s.getShape()).collect(Collectors.toList());

                //Определяем выигрышную фигуру
                IPlayer.Shape winShape = defineWinningShape(shapes);

                //Собираем всех игроков с выигрышной фигурой и отправляем в след. раунд
                if(winShape!=UNDEFINED)
                    players = players.stream().filter(s -> s.getShape() == winShape).collect(Collectors.toList());


                System.out.println("Оставшиеся игроки после бросания");
                System.out.println(showPlayersFigures(players));
                System.out.println("______________________________________");
            }while(players.size()>1);

            //увеличиваем очки победителя раунда
            for(IPlayer p: players) {
                p.setScore(p.getScore() + 1);
            }

            System.out.print("\nПобедитель(и) раунда:  ");
            System.out.println(showPlayers(players));

            //============LOG================
            log.info("Round winner {}",showPlayers(players));
            //============ENDLOG================

            //============LOG================
            gamesPlayed++;
            gamesLeft--;
            log.info("Played games {}. Games left: {}\n", gamesPlayed, gamesLeft);
            //============ENDLOG================
        }

        //Вычисление самого высокого результата
        int highestScore = getHighestScore(players);
        ArrayList<IPlayer> winners = new ArrayList<>();
        for (IPlayer p: rs.players) {
            if(p.getScore() == highestScore){
                winners.add(p);
            }
        }

        //Вывод игроков с таким же количеством очков
        System.out.println("\n === Победители === ");
        System.out.println(showPlayers(winners));

        //Запись результатов в файл
        writeResults(rs.players, winners);
        log.info("Программа завершена");
    }
    public static IPlayer.Shape defineWinningShape(List<IPlayer.Shape> shapes){
        if(shapes.size() >=3 || shapes.size() <= 1){
            return UNDEFINED;
        }
        else{
            if(shapes.contains(SCISSORS))
                return shapes.contains(PAPER)? SCISSORS: STONE;
            if(shapes.contains(STONE))
                return shapes.contains(SCISSORS)? STONE: PAPER;
        }
        return UNDEFINED;
    }
    public static String showPlayersFigures(List<IPlayer> players){
        StringBuilder txt = new StringBuilder();
        for (IPlayer p:players) {
            txt.append(p).append(".  Фигура = ").append(p.getShape().getName()).append("\n");
        }
        return txt.toString();
    }
    public static String showPlayers(List<IPlayer> players){
        StringBuilder txt = new StringBuilder();
        for (IPlayer p:players) {
            txt.append(p).append("\n");
        }
        return txt.toString();
    }
    public static int getHighestScore(List<IPlayer> players){
        int highestScore = -1;
        for (IPlayer player : players) {
            if (player.getScore() > highestScore) {
                highestScore = player.getScore();
            }
        }
        return highestScore;
    }
    public static void writeResults(List<IPlayer> players, List<IPlayer> winners){
        try {
            log.info("Запись результатов");
            File file = new File("src/main/java/hw_16_v2/info/Results.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter) ;
            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("Дата yyyy.MM.dd 'Время' hh:mm:ss");
            printWriter.println(formatForDateNow.format(dateNow));
            printWriter.println("РЕЗУЛЬТАТЫ:");
            int n = 1;
            for (IPlayer p: players) {
                printWriter.println(n + ". " + p);
                n++;
            }
            printWriter.println("ПОБЕДИТЕЛЬ(И):");
            n = 1;
            for (IPlayer p: winners) {
                printWriter.println(n + ". " + p);
                n++;
            }
            printWriter.println();
            printWriter.close();
            log.info("Результаты успешно записаны\n");
        } catch (IOException e){
            System.out.println(e);
            log.error("Ошибка при записи файла");
            System.out.println("Ошибка при записи результатов");
        }
    }

}
