package org.example;

import java.util.List;
import java.util.Scanner;

public class ThreadPractice {
    public static void main(String[] args) {


        new ThreadPractice();
    }
    public ThreadPractice(){
        //class1();
        //class2();
        //mainPracticeClass2();
        //classThread();


    }
    public void class1(){
        Thread myThread=new Thread(()->{// יוצרים פונקציה על מנת שהתהליכון ידע מה לעשות
            System.out.println("Hello  from thread first !");
        });//מצפה לקבל בבנאי פונקציה - פונקציה ריקה ללא פרמטרים
        myThread.start();// נדרש להפעיל את התהליכון על מנת שיפעל

        //ניתן לבצע בקיצור ללא מתן שם
        new Thread(()->{ //נכנס למצב new
            // בפונקציה אנונימית עבודה עם קבוע שלא משתנה בלבד ולא עובד עם שדות ומשתנים ושיתוף מידע
            System.out.println("Hello  from thread second !");
            try {
                Thread.sleep(100); // מעבר למצב Blocked
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();// מעבר למצב runnable
        //.stop // פונקציה מפסיקה ומעיפה את התליכון, לא מסיימת את כל התהליכים כיבוי פתאומי
        //run () // התהליכון לא יעבוד בצורה מקבילית כי תעדפנו את התליכון ויעבוד בצורה סדרתית
        printNumber(1);//תהליכון ראשון יופעל
        printNumber(2);// תהליכון שני יופעל
        System.out.println("DONE");// תמיד יודדפס ראשון לפני הדפסת התהליכונים
        //* אין הגדרה לסדר ומתבצע באופן רנדומאלי בין התהליכונים של הפונקציית הדפסה


    }
    public void printNumber(int num){
        new Thread(()->{
            while (true){
                System.out.println(num);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    public void class2(List<Integer> number){
        Thread myThead=new Thread(()->{
            int countDuplicates=0;
            for(int i=0;i<number.size();i++){
                for (int j=0;j<number.size();j++){
                    if(number.get(i).equals(number.get(j))){
                        countDuplicates++;
                        break;
                    }
                }
            }
            System.out.println("The number of duplicates is : "+ countDuplicates);
        });
        if(number.size()>1000){
        myThead.start(); // במידה ומדובר בפעולה יקרה וארוכה נבצע בצורה מקבילית
        }else {
            myThead.run();// במידה ומדובר בפעולה פשוטה יחסית נסצע בצורה סדרתית
        }
    }
    public void mainPracticeClass2(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Insert your number:");
        int num=scanner.nextInt();
        for(int i=0;i<num;i++){
            printNumber(i);
        }

    }
    public void practiceClass2(int num){
        new Thread(()->{
            while (true){
                System.out.println(num);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }
    public void classThread(){
        TargilThread targilThread=new TargilThread("first",10);
        targilThread.start();
    }

}
class TargilThread extends Thread{ // הורשה של תהליכון
    // מאפשר עבודה על משתנים ושיתוף מידע
    private String path;
    private int times;
    private int seconds;
    private final int INITIAL;
    public TargilThread (String path,int seconds){
        this.path=path;
        this.seconds=seconds;
        this.INITIAL=seconds;
    }
    public void run(){
        // להכניס את הפעולות שרוצים שיבצע
        this.times++;// סופר כמה פעמים התהליכון הופעל, לא היה ניתן לעשות זאת בפונקציה אננומית
        while (this.seconds>0){
            System.out.println(this.seconds);
            this.seconds--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.seconds=INITIAL;

    }

}
class TargilThreadRunnable implements Runnable { // מחלקה שיורשת ממשק runnable
    @Override
    public void run() {

    } // הורשה של תהליכון


    }




