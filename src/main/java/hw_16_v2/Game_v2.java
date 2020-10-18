package hw_16_v2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static hw_16_v2.IPlayer.Shape.*;

public class Game_v2 {
    private static final Logger log =  LoggerFactory.getLogger(Game_v2.class);
    public static void main(String[] args) {
        Locale loc = new Locale(args[2]);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("roshambo", loc);
        ResourceBundle rbLog = ResourceBundle.getBundle("roshamboLog", loc);
        //============LOG================
        log.info(rbLog.getString("info_startGame"));
        //============ENDLOG================
        String answer;
        int numOfGames = 0;
        int humans = 0;
        int computers = 0;
        List<IPlayer> players = new ArrayList<>();

        System.out.println(resourceBundle.getString("rules"));
        Scanner s_num = new Scanner(System.in);
        //Задание пользователем кол-ва игроков
        while(computers+humans<=1){
            do{
                System.out.print(resourceBundle.getString("input_amountOfHumans"));
                while (!s_num.hasNextInt()){
                    System.out.print(resourceBundle.getString("input_amountOfHumans"));
                    s_num.next();
                }
                humans = s_num.nextInt();
            } while (humans<0);

            do{
                System.out.print(resourceBundle.getString("input_amountOfComputers"));
                while (!s_num.hasNextInt()){
                    System.out.print(resourceBundle.getString("input_amountOfComputers"));
                    s_num.next();
                }
                computers = s_num.nextInt();
            } while (computers<0);

            do{
                System.out.print(resourceBundle.getString("input_amountOfGames"));
                while (!s_num.hasNextInt()){
                    System.out.print(resourceBundle.getString("input_amountOfGames"));
                    s_num.next();
                }
                numOfGames = s_num.nextInt();
            } while (numOfGames<0);
        }
        //=========LOG===========
        log.info(rbLog.getString("info_amountOfGames").concat("{}"), numOfGames);
        //=========ENDLOG===========
        RoshamboSession_v2 rs = new RoshamboSession_v2(computers, humans, numOfGames);
        //Добавление компьютеров
        int i;
        for (i = 0; i < computers; i++) {
            rs.players.add(new ComputerPlayer(i, resourceBundle));
        }
        humans+=i;
        //Добавление людей
        for (; i < humans; i++) {
            rs.players.add(new HumanPlayer(i, resourceBundle));
        }
        Scanner s_string = new Scanner(System.in);

        //Logger vars
        int gamesLeft = numOfGames;
        int gamesPlayed = 0;

        //Цикл раунда
        for (int j = 0; j < numOfGames; j++){
            //Список игроков для изменения
            players = new ArrayList<>(rs.players);

            //Вопрос о прерывании игры каждый раунд
            System.out.print(resourceBundle.getString("q_continue"));
            answer = s_string.nextLine();
            if(answer.toLowerCase().equals("q"))
                break;

            //Цикл определения победителя
            do {
                //Выбор игроков
                System.out.println();
                for(IPlayer p: players){
                    p.makeChoice();
                }
                //Вывод игроков и компьютерв с их выборами
                System.out.println(resourceBundle.getString("info_playersFigures"));
                System.out.println(showPlayersFigures(players, resourceBundle));

                //============LOG================
                log.info("\n{}",showPlayersFigures(players, resourceBundle));
                //============ENDLOG================

                //Убираем повторяющиейся фигуры
                List<IPlayer.Shape> shapes = players.stream().distinct().map(s -> s.getShape()).collect(Collectors.toList());

                //Определяем выигрышную фигуру
                IPlayer.Shape winShape = defineWinningShape(shapes);

                //Собираем всех игроков с выигрышной фигурой и отправляем в след. раунд
                if(winShape!=UNDEFINED)
                    players = players.stream().filter(s -> s.getShape() == winShape).collect(Collectors.toList());


                System.out.println(resourceBundle.getString("info_remainingPlayers"));
                System.out.println(showPlayersFigures(players, resourceBundle));
                System.out.println("______________________________________");
            }while(players.size()>1);

            //увеличиваем очки победителя раунда
            for(IPlayer p: players) {
                p.setScore(p.getScore() + 1);
            }

            System.out.print(resourceBundle.getString("info_roundWinner"));
            System.out.println(showPlayers(players));

            //============LOG================
            log.info(rbLog.getString("info_roundWinner").concat("{}"),showPlayers(players));
            //============ENDLOG================

            //============LOG================
            gamesPlayed++;
            gamesLeft--;
            log.info(rbLog.getString("info_playedGames").concat("{}. ").concat(rbLog.getString("info_gamesLeft")).concat("{}"), gamesPlayed, gamesLeft);
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
        System.out.println(resourceBundle.getString("info_absWinner"));
        System.out.println(showPlayers(winners));

        //Запись результатов в файл
        writeResults(rs.players, winners, resourceBundle, rbLog);
        //=============LOG=================
        log.info(rbLog.getString("info_finishGame"));
        //=============ENDLOG=================
    }
    public static IPlayer.Shape defineWinningShape(List<IPlayer.Shape> shapes){
        if(shapes.size() >=3 || shapes.size() <= 1){
            return UNDEFINED;
        }
        else{
            if(shapes.contains(SCISSORS))
                return shapes.contains(PAPER)? SCISSORS: ROCK;
            if(shapes.contains(ROCK))
                return shapes.contains(SCISSORS)? ROCK : PAPER;
        }
        return UNDEFINED;
    }
    public static String showPlayersFigures(List<IPlayer> players, ResourceBundle resourceBundle){
        StringBuilder txt = new StringBuilder();
        for (IPlayer p:players) {
            txt.append(p).append(".  ").append(resourceBundle.getString("shape")).append(" = ").append(p.getShapeName()).append("\n");
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
    public static void writeResults(List<IPlayer> players, List<IPlayer> winners, ResourceBundle resourceBundle,ResourceBundle rbLog){
        try {
            log.info(rbLog.getString("info_writingToFile"));
            File file = new File("src/main/java/hw_16_v2/info/Results.txt");
            FileWriter fileWriter = new FileWriter(file, true);
            PrintWriter printWriter = new PrintWriter(fileWriter) ;
            printWriter.println(printData(resourceBundle.getLocale()));
            printWriter.println(resourceBundle.getString("info_results"));
            int n = 1;
            for (IPlayer p: players) {
                printWriter.println(n + ". " + p);
                n++;
            }
            printWriter.println(resourceBundle.getString("info_absWinner"));
            n = 1;
            for (IPlayer p: winners) {
                printWriter.println(n + ". " + p);
                n++;
            }
            printWriter.println();
            printWriter.close();
            //=========LOG===========
            log.info(rbLog.getString("info_writeSuccess"));
            //=========ENDLOG===========
        } catch (IOException e){
            System.out.println(e);
            log.error(rbLog.getString("IOException"));
            System.out.println(resourceBundle.getString("error_IOException"));
        }
    }
    private static String printData(Locale loc) {
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, loc);
        return  df.format(new Date());
    }

}
