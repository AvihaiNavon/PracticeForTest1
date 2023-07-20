package org.example;

import java.util.*;
import java.util.stream.IntStream;

public class Compare {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        new Compare();
    }
    public Compare(){
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
