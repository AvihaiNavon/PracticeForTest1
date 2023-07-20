package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TargilClassThreadBalance {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        new TargilClassThreadBalance();
    }


    public TargilClassThreadBalance(){
        //multiThreadProblem();//בעיית ריבוי תהליכונים
        //multiThreadSolution();// פתרון ריבוי תהליכונים
        //consumerProducerProblem(); //בעיית יצרן צרכן
        PhilisopherProblem();

    }
    public void multiThreadProblem(){
        CrowdFunding project=new CrowdFunding();
        // במקרה כזה מתבצע מרוץ תהליכונים שנוצרים מצבים רבים שתהליכון גורס תהליכון
        new Thread (()->{
            for(int i=0;i<100000;i++) {
                project.deposit(1);
            }
        }).start();
        new Thread(()->{
            for(int i=0;i<100000;i++){
                project.deposit(2); //קייפ משאב משותף שפונים לאותה פונקציה שני תהליכונים
            }
        }).start();

        System.out.println(project.getBalance()); //  התוצאה לא תיהיה כפי שתכננו בגלל גריסת תהליכונים
        //אם נפעיל תהליכון אחד התוצאה תיהיה אפס כי פעולת ההדפסה מתבצעת לפני התהליכון

    }
    public void multiThreadSolution(){
        CrowdFunding project=new CrowdFunding();
        // במקרה כזה מתבצע מרוץ תהליכונים שנוצרים מצבים רבים שתהליכון גורס תהליכון
        Thread t1=new Thread (()->{
            for(int i=0;i<100000;i++) {
                project.deposit2(1);// הוספת מנעול למתודה
            }
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<100000;i++){
                project.deposit2(2); //קייפ משאב משותף שפונים לאותה פונקציה שני תהליכונים
            }
        });
        t1.start();
        t2.start();
        try {
            //t1.start();
            t1.join();//תתקע פה עד שיגמר התליכון
            //t2.start();
            t2.join();//תתקע פה עד שיגמר התהליכון
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(project.getBalance()); //  התוצאה לא תיהיה כפי שתכננו בגלל גריסת תהליכונים
        //אם נפעיל תהליכון אחד התוצאה תיהיה אפס כי פעולת ההדפסה מתבצעת לפני התהליכון

    }
    public void consumerProducerProblem(){
        // פתרון כאשר מדובר על תקשורת בין שני תהליכונים אבל לא לקיבוי תהליכונים
        ArrayList<Integer> warehouse=new ArrayList<>();// ייצוג מחסן
        Thread farmerThread=new Thread(()->{ // עבודה חקלאי
            while (true) {
                doWork();
                synchronized (warehouse) { // הגדרת משתנה להכנסה למצב המתנה
                    if (warehouse.size() < 5) {// אם המחסן לא מלא
                        warehouse.add(warehouse.size());
                        System.out.println("Farmer added orange: " + warehouse.size());// ממלא את המחסן
                        warehouse.notify();
                    } else {
                        try {
                            System.out.println("Warehouse is full, orange was thrown in the trash!");//נזרק כי המחסן מלא
                            warehouse.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
            }
        });

        Thread driverThread=new Thread(()->{ //עבודה נהג
            while (true) {
                synchronized (warehouse) {
                    doWork();
                    if (!warehouse.isEmpty()) {
                       // warehouse.clear();// תרוקן את הרשימה/ מחסן
                        int orange=warehouse.get(0);
                        warehouse.remove(orange);
                        System.out.println("Driver took the orange bag "+ orange);
                        warehouse.notify();// מפעיל את הפונקציה במידה והיא במצב wait
                    } else {
                        try {
                        System.out.println("No oranges in the warehouse, what a waste!");
                            warehouse.wait();// מכניס למצב wait
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
            }
        });
        farmerThread.start();
        driverThread.start();
    }
    public void doWork(){
        try {
            Thread.sleep(new Random().nextInt(1000)); // זמן עבודה אקראי
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void PhilisopherProblem(){
        Fork f1=new Fork(1);
        Fork f2=new Fork(2);
        Fork f3=new Fork(3);
        Fork f4=new Fork(4);
        Fork f5=new Fork(5);
        Philisopher p1=new Philisopher(1,f1,f2);
        Philisopher p2=new Philisopher(2,f2,f3);
        Philisopher p3=new Philisopher(3,f3,f4);
        Philisopher p4=new Philisopher(4,f4,f5);
        Philisopher p5=new Philisopher(5,f5,f1);

    }



}
class CrowdFunding{
    private int balance;
    public  void  deposit(int amount){
        this.balance += amount;
    }
    public synchronized void  deposit2(int amount){ //הוספת מנעול כך שתהליכון אחד יופעל כל פעם על הפונקציה
        this.balance += amount;
    }
    public String toString(){
        return "Balance is "+this.balance;
    }

    public int getBalance() {
        return balance;
    }
}
class Philisopher extends Thread{
    private int id;
    private Fork right;
    private Fork left;
    public Philisopher(int id,Fork right,Fork left){
        this.id=id;
        this.right=right;
        this.left=left;
    }
    public void run(){
        while (true){
            this.think();
            this.tryToEat();
        }
    }
    public void think(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void tryToEat(){
        synchronized (this.right){
            if(this.right.isAvailable()){
                this.right.pick();
                eat();
                synchronized (this.left){
                    if(this.left.isAvailable()){
                        this.left.pick();
                    }
                }
            }

        }
    }
    private void eat(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
class Fork{
    private int id;
    private boolean picked;
    public Fork(int id){
        this.id=id;
        this.picked=false;
    }
    public boolean isAvailable(){
        return !this.picked;
    }
    public void pick(){
        this.picked=true;
    }
    public void drop(){
        this.picked=false;
    }


}
