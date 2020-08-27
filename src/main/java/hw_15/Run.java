package hw_15;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        String answ = "";
        int len = 5;
        int[] num_arr = new int[len];
        String ch = "#";
        Scanner s = new Scanner(System.in);
        System.out.println("Введите последовательность цифр (макс длина 9)");
        System.out.println("Чтобы выйти введите \"-1\"");
        while (true){
            System.out.println("");
            int c = 0;
            while (c!=len){
                System.out.printf("цифра %d/%d => ",c+1,len);
                while (!s.hasNextInt()){
                    System.out.printf("Некорректный ввод. Повторите цифра %d/%d => ",c+1,len);
                    s.next();
                }
                num_arr[c] = s.nextInt();
                if(Math.abs(num_arr[c])>=10){
                    System.out.println("Цифра, а не число");
                    continue;
                }
                c++;
            }
            StringConvertor sc = new StringConvertor(70, 20, num_arr);
            System.out.print("Символ для отрисовки =>");
            s = new Scanner(System.in);
            ch = s.nextLine();
            sc.Draw(ch);
        }
    }
}
