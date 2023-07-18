package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TryCatch {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        new TryCatch();
    }

    public TryCatch(){
        //DivisionOfNumbers();
        //ArrayProblem();
        //StringProblem();
    }
    public void DivisionOfNumbers() {
        Scanner scanner = new Scanner(System.in);
        //  try // כאשר נימצא פה התוכנית תיעצר ברגע שיהיה בעיה
        int num1 = 0;//על מנת שיוכל להתייחס לחריגה צריך להגדיר לפני
        int num2 = 0;
        int result = 0;
        while (true) {
            try {
                System.out.println("Insert Number 1: ");
                num1 = scanner.nextInt();
                System.out.println("Insert Number 2: ");
                num2 = scanner.nextInt();
                result = num1 / num2;
                System.out.println("RESULT: " + result);
            } catch (ArithmeticException e) {// חריגה ממוקדת לבעיית ספרות כמו חלוקה באפס
                e.printStackTrace();
                System.out.println("Something went wrong- Could be a miscarriage at zero");
                System.out.println("The entered mathematical expression: " + num1 + " / " + num2);
            } catch (InputMismatchException e) { // מטפל בבעיות של הכנסת קלט
                e.printStackTrace();
                System.out.println("Something went wrong-You may have entered words and not numbers");
                scanner.next();// חשוב להכניס אם לא יתבצע לופ
            } catch (Exception e) { // במידה ויש עוד תקלה שלא חשבנו עלייה
                e.printStackTrace();
                System.out.println("Something went wrong");
            }
            // הסיבה להשתמש במספר catch על מנת לטפל בכל מקרה לגופו, חשוב לשים בסוף את הבדיקת חריגה הכללית
        }

    }
    public void ArrayProblem(){
        int array[]= {5,4,3,2,21,1,7};
        int i=0;
        try {
            for( i=0;i<=array.length;i++){
                System.out.print(array[i]+","); // גלישה ממערך
            }

        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            System.out.println();
            System.out.println("An array may have been browsed");
            System.out.println("You have checked to a location " +(i+1)+ " when there are only "+array.length+  " spots");
        }


    }
    public void  StringProblem(){
        String name=null;
        try {
            System.out.println("The length yout name:" + name.length());
        }catch ( NullPointerException e){
            e.printStackTrace();
        }
        System.out.println("Bye!");
    }

}
