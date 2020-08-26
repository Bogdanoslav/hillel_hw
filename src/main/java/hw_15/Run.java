package hw_15;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        String answ = "";
        int c = 9;
        int num = 0;
        String ch = "#";
        Scanner s = new Scanner(System.in);
        System.out.println("Введите последовательность цифр (макс длина 9)");
        System.out.println("Чтобы выйти введите \"-1\"");
        while (num!=-1){
            System.out.println("");
            System.out.print("число => ");
            while (!s.hasNextInt()){
                System.out.print("Некорректный ввод. Повторите => ");
                s.next();
            }
            num = s.nextInt();
            StringConvertor sc = new StringConvertor(70, 20, String.valueOf(num));
            System.out.print("Символ для отрисовки =>");
            s = new Scanner(System.in);
            ch = s.nextLine();
            sc.Draw(ch);
        }
    }
}
