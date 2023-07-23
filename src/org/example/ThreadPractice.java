package org.example;

import java.util.*;

public class ThreadPractice {
    public static void main(String[] args) {


        new ThreadPractice();
    }
    public ThreadPractice(){
        //class1();
        //class2();
        //mainPracticeClass2();
        //classThread();
        //class3();
        //class4();
        //carRace();
        class5();


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
    public void class3(){
        Random random=new Random();
        //Clock clock=new Clock();
        //clock.start();
        //firstToOneHundred();

        int n=100;
        int [] array=new int[n];
        for(int i=0;i<n;i++){
            array[i]=random.nextInt(1,5);
        }
        int total=1;
        for (int j=0;j<n;j++){
            total+=array[j];
        }
        System.out.println("Total is : "+ total);
        int part=n/10;
        List<Thread> allThread=new ArrayList<>();
        for(int i=0;i<part;i++){
            int index=i*10;
           Thread t= new SumNumber(index,(index+part),array);
           allThread.add(t);
           t.start();
        }
       for (Thread t:allThread){ // חשוב לצור את כל התהליכונים לטובת ההדפסה
           try {
               t.join();
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }
        System.out.println("Total Thread is : "+ SumNumber.sum);

    }
    public void firstToOneHundred() {
        for (int i = 0; i < 5; i++) {
            RunToOneHundred runToOneHundred = new RunToOneHundred(i);
            runToOneHundred.start();
        }

        while (true) {
            if (RunToOneHundred.scoreboard.size() == 5) {
                System.out.println(RunToOneHundred.scoreboard);
                break;
            }
        }
    }
    public void class4(){


     List<Customer> customers=new ArrayList<>();
     customers.add(new Customer(1));
        customers.add(new Customer(2));
        customers.add(new Customer(3));
     CoffeeShop coffeeShop=new CoffeeShop();
     coffeeShop.start();
     new Thread(()->{
             while (!customers.isEmpty()){
                 System.out.println(customers);
                 for (Customer customer:customers){
                     if (!customer.isGetCoffee()){
                         coffeeShop.coffeeReady=false;
                         customer.setGetCoffee(true);
                     }
                 }
             }
     }).start();


    }
    public void carRace(){
        List<Car>cars=new ArrayList<>();
        for(int i=0;i<5;i++){
            new Car((i+1)).start();
        }
        for (Car car:cars){
            try {
                car.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Car.carsOver);
    }
    public void class5(){
         List<Product1> products=new ArrayList<>();
        new Thread(()->{
            Producer producer=new Producer(products);
            producer.start();
        }).start();
        new Thread(()->{
            Consumer consumer=new Consumer(products);
            consumer.start();
        }).start();


}
}
class Product1{
    private String name;
    public Product1(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return "Product1{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product1 product1)) return false;
        return Objects.equals(name, product1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
class Producer extends Thread {
    private List<Product1> products;
    public List<String> names= Arrays.asList("asd","fdfd","dfasg","fferfe","sgas");
    private Random random = new Random();

    public Producer(List<Product1> products) {
        this.products=products;

    }
    public void addToList(){
        Product1 p=new Product1(names.get(random.nextInt(names.size())));
        System.out.println("Add: "+ p);
        products.add(p);
        System.out.println(products);
    }

    public   List<Product1> getProducts() {
        return products;
    }
    public void run() {
        while (true) {
            synchronized (products) {
                addToList();
                products.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
class Consumer extends Thread {
    private Random random = new Random();
    private Object object = new Object();
    private List<Product1> products;

    public Consumer(List<Product1> products) {
        this.products = products;
    }

    public void run() {

        while(true) {
            synchronized(products) {
                if(products.isEmpty()) {
                    try {
                        System.out.println("List is empty, waiting...");
                        products.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Product1 p = products.remove(0);
                    System.out.println("Removed: " + p);
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
        }

class Car extends Thread {
    public static List<Integer> carsOver = new ArrayList<>();
    private int id;
    private int distance;
    private boolean raceOver;
    Random random = new Random();

    public Car(int id) {
        this.id = id;
        distance = 0;
        raceOver = false;
    }

    public void run() {
        while (distance <= 100) {
            distance += random.nextInt(3, 5);
        }
        System.out.println("FINIS " + id);
        carsOver.add(id);

    }
}
class Customer{
    private int id;
    private boolean getCoffee;
    public Customer(int id){
        this.id=id;
        getCoffee=false;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", getCoffee=" + getCoffee +
                '}';
    }

    public void setGetCoffee(boolean getCoffee) {
        this.getCoffee = getCoffee;
    }

    public boolean isGetCoffee() {
        return getCoffee;
    }
}
class CoffeeShop extends Thread{
    public static boolean coffeeReady=true;

    private Object makeCoffee=new Object();;
    public void makeCoffee(){

        System.out.println("Making coffee");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Coffee is ready!");
        coffeeReady=true;
    }
    public void run() {
        while (true){
            synchronized (makeCoffee){
                if(!coffeeReady) {
                    System.out.println(" Customer: " + " is waiting");
                    makeCoffee();

                    makeCoffee.notify();


                }else {
                    System.out.println("Customer: "+" received coffee");
                    try {
                        makeCoffee.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }


        }
    }

    }


class SumNumber extends Thread{
    private int index;
    private int part;
    private int [] array;
    public static int sum=1;
    public SumNumber(int index,int part,int [] array){
        this.index=index;
        this.part=part;
        this.array=array;

    }
    public void run(){
        for (int i=index;i<part;i++){
            sum+=array[i];
        }
        System.out.println("WORK"+ index);
    }
}
class RunToOneHundred extends Thread{
    Random random=new Random();
    private int num;
    private int id;
    public static List<Integer> scoreboard=new ArrayList<>();
    public RunToOneHundred(int id){
        this.id=id;
    }
    @Override
    public void run() {
        while (num<=10000) {
            num+=random.nextInt(0,5);
        }
    scoreboard.add(id);
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
class Clock extends Thread{
    private int sec;
    private int min;
    private int hour;

    public Clock(){
        sec=0;
        min=0;
        hour=0;

    }
    public void run(){
        while (true){
            try {
                Thread.sleep(1000);
                sec++;
                if(sec==60){
                    sec=0;
                    min++;
                }
                if (min==60){

                    min=0;
                    hour++;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Clock: "+ hour +":"+min+":"+sec);
        }
    }

        }




