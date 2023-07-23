package org.example;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Compare {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        new Compare();
    }
    public Compare(){
       // compare1();
        //compare2();
        //compare3();
        //compare4();
        compare5();

    }
    public void compare1(){
        Random random=new Random();
        List<Product> products=new ArrayList<>();
        Product.compareProduct=CompareProduct.NAME;
        IntStream.range(0,10).forEach(item->products.add(new Product("P", random.nextInt(10), random.nextInt(10,20))));

        Product p1=new Product("A",5,8);
        products.add(p1);
        Product p2=new Product("B",5,8);
        products.add(p2);
        System.out.println(products);
        Collections.sort(products);
        System.out.println(products);
        int index=Collections.binarySearch(products,p1);
        System.out.println(index+","+products.get(index));

    }
    public void compare2(){
        List<Person> people=Arrays.asList(new Person("ac","ab"),new Person("ab","ab"),new Person("ac","cde"),new Person("da","dc"));
        System.out.println(people);
        people=people.stream().sorted().toList();
        System.out.println(people);
        int i = Collections.binarySearch(people,new Person("ab", "ab"));
        System.out.println(i);
    }
    public void compare3(){
        MyDate myDate1=new MyDate(17,11,1994);
        MyDate myDate12=new MyDate(17,12,1994);
        MyDate myDate2=new MyDate(24,4,1995);
        MyDate myDate22=new MyDate(25,4,1995);
        MyDate myDate3=new MyDate(1,9,1996);
        MyDate myDate4=new MyDate(1,9,2000);
        //List<MyDate> myDates=new ArrayList<>(Arrays.asList(myDate1,myDate12,myDate2,myDate22,myDate3));
        List<MyDate> myDates=new ArrayList<>();
        myDates.add(myDate1); myDates.add(myDate12); myDates.add(myDate2); myDates.add(myDate22); myDates.add(myDate3);
        System.out.println(myDates);
        System.out.println(myDates.contains(myDate4));
        myDates=myDates.stream().sorted().toList();
        System.out.println(myDates);
        int i=Collections.binarySearch(myDates,myDate1);
        System.out.println(i);


    }
    public void compare4(){
        List<myColor> myColors=new ArrayList<>();
        myColors.add(new myColor("avihai","blue"));
        myColors.add(new myColor("noa","yellow"));
        myColors.add(new myColor("itai","red"));
        System.out.println(myColors);
        myColors=myColors.stream().sorted().toList();
        System.out.println(myColors);
        System.out.println(myColors.contains(new myColor("avihai","v")));
    }
    public void compare5(){
        Score score1=new Score("av",5);
        Score score2=new Score("ab",5);
        Score score3=new Score("bc",3);
        Score score4=new Score("hy",2);
        Score score5=new Score("er",1);
        List<Score> scores=new ArrayList<>(Arrays.asList(score2,score1,score5,score4,score3));
        System.out.println(scores);
        scores=scores.stream().sorted().toList();
        System.out.println(scores);
        int i=Collections.binarySearch(scores,new Score("av",5));
        System.out.println(i);
        System.out.println(scores.contains(new Score("av",5)));



    }


}
class myColor implements Comparable<myColor>{
    private static final List<String> COLOR=Arrays.asList("yellow","blue","red");
    private String name;
    private String color;

    public myColor(String name,String color) {
        this.name = name;
        this.color=color;
    }

    @Override
    public int compareTo(myColor o) {

        return COLOR.indexOf(color)-COLOR.indexOf(o.color);
    }
    public boolean equals(Object o){
        if (this==o) return true;
        if (name.equals(((myColor)o).name)&& color.equals(((myColor)o).color))return true;
        return false;
    }

    @Override
    public String toString() {
        return "myColor{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
class Car1 implements Comparable<Car1>{
    private String make;
    private String model;
    private int year;

    public Car1(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public int compareTo(Car1 c) {
        int result = make.compareTo(c.make);
        if (result == 0) {
            result = this.model.compareTo(c.model);
            if (result == 0) {
                result = this.year - c.year;
            }
        }
        return result;
    }

}
class MyDate implements Comparable<MyDate>{
    private int day;
    private int month;
    private int year;

    public MyDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public int compareTo(MyDate o) {
        int result=o.year-this.year;
        if (result==0){
            result=o.month-this.month;
            if (result == 0) {
                result=o.day-this.day;
            }
        }
        return result;
    }
//    public boolean equals(Object object){
//        if (object==this)return true;
//        if (this.day==((MyDate)object).day &&this.month==((MyDate)object).month && this.year==((MyDate)object).year)return true;
//        return false;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyDate myDate)) return false;
        return day == myDate.day && month == myDate.month && year == myDate.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    @Override
    public String toString() {
        return "MyDate{" +
                 + day +
                "/" + month +
                "/" + year +
                '}';
    }
}
class Shirt implements Comparable<Shirt>{
    private String type;
    private String color;
    private int size;

    public Shirt(String type, String color, int size) {
        this.type = type;
        this.color = color;
        this.size = size;
    }

    @Override
    public int compareTo(Shirt o) {
        int result=this.type.compareTo(o.type);
        if(result==0){
            result=this.color.compareTo(o.color);
            if (result==0){
                result=this.size-o.size;
            }
        }
        return result;
    }
}
class Score implements Comparable<Score>{
    private String name;
    private int scoreResult;

    public Score(String name, int scoreResult) {
        this.name = name;
        this.scoreResult = scoreResult;
    }
    @Override
    public int compareTo(Score o) {
        int result= o.scoreResult-this.scoreResult;
        if(result==0){
            result=this.name.compareTo(o.name);
        }
        return result;
    }
    public boolean equals(Object o){
        if (this==o)return true;
        if (this.scoreResult==((Score)o).scoreResult && this.name.equals(((Score)o).name))return true;
        else return false;

    }

    @Override
    public String toString() {
        return "Score{" +
                "name='" + name + '\'' +
                ", scoreResult=" + scoreResult +
                '}';
    }

}
class Person implements Comparable<Person>{
    private String firstName ;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        int result=lastName.compareTo(o.lastName);
        if(result==0)result=firstName.compareTo(o.firstName);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return ((Person) o).firstName.equals(this.firstName) && ((Person) o).lastName.equals(lastName);

    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName());
    }
}

class Book{
    private String title;
    private int year;
    public int compareTo(Book o){ // יצירת הגדרת מיון לפי סדר טבעי שנגדיר
        // מחזיר מספר כאשר גדול מאפס אומר שהאובייקט יותא גדול ממה שנכנס ושלישי אומר ההפך, אפס שווים
        int result=this.title.compareTo(o.title); // משווה בין כותרות כאשר משתמשים בפונקציית השווה של מחרוזת
        if (result==0){
            result=this.year-o.year; // משווב לפי שנים
        }
        return result;
    }
}
class Product implements Comparable<Product> {
    private String name;
    private int amount;
    private int price;
    public static CompareProduct compareProduct;
    public Product(String name,int amount,int price){
        this.name=name;
        this.amount=amount;
        this.price=price;
    }

    public int compareTo(Product o){
        int result=0;
        switch (compareProduct){
            case NAME -> result=this.name.compareTo(o.name);
            case AMOUNT -> result=this.amount-o.amount;
            case PRICE -> result=this.price-o.price;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
enum CompareProduct{
    NAME,AMOUNT,PRICE
}
