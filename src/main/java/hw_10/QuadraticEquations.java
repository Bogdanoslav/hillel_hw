package hw_10;

import java.util.Scanner;

public class QuadraticEquations {
    public static void main(String[] args) {
        /*
                ====================
                Квадратные уравнения
                ====================
         */
        Scanner input = new Scanner(System.in);
        double a = 0, b ,c;
        while(a==0){
            System.out.print("a => ");
            a = input.nextDouble();
        }
        System.out.print("b => ");
        b = input.nextDouble();
        System.out.print("c => ");
        c = input.nextDouble();
        System.out.println(a + " " + b + " " + c);
        solveQE(a,b,c);

    }
    public static void solveQE(double a,double b,double c){
        double d  = b*b-(4*a*c);
        double x1;
        double x2;
        if(d<0){
            System.out.println("D<0");
        }
        else if(d!=0){
            x1 = (-b - Math.sqrt(d))/(2*a);
            x2 = (-b + Math.sqrt(d))/(2*a);
            System.out.print("x1 = " + x1 + "   x2 = " + x2);
        }
        else{
            x1 = -b/2*a;
            System.out.printf("x = %f", x1);
        }
    }
}
