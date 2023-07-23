package org.example;

import java.util.*;

public class Generics {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        new Generics();
    }
    public Generics(){
        //Generics1();
        //Generics2();
        Generics3();

    }
    public void Generics1(){
        Pair<Integer,String>pair=new Pair<>(1,"avihai");
        System.out.println(pair);
        System.out.println(swap(pair));
    }
    public void Generics2(){
        Box<Integer> boxInt=new Box<Integer>();
        Box<String>boxString=new Box<String>();
        System.out.println(boxInt);
        System.out.println(boxString);
        boxInt.set(3);
        boxString.set("asdfsa");
        System.out.println(boxInt);
        System.out.println(boxString);

    }
    public Pair swap (Pair pair){
        Pair pairSwap=new Pair(pair.getSecond(),pair.getFirst());
        return pairSwap;
    }
    public void Generics3(){
        Pair2<Integer,String>pair2=new Pair2<>(5,"sdgvfd");
        System.out.println(pair2.getT());
        System.out.println(pair2.getS());
        pair2.setT(8);
        pair2.setS("afda");
        System.out.println(pair2.getT());
        System.out.println(pair2.getS());
        Pair2<Boolean,String>pair3=new Pair2<>(true,"sdgvfd");
        System.out.println(pair3.getT());
        System.out.println(pair3.getS());
        pair3.setT(false);
        pair3.setS("afda");
        System.out.println(pair3.getT());
        System.out.println(pair3.getS());



    }


}
class MapCache<T,M>{
    private T key;
    private M value;
    private HashMap<T,M> tmHashMap;
    public MapCache(T t,M m){
        tmHashMap=new HashMap<>();
        this.key=t;
        this.value=m;
    }
    public void put(T t,M m){
        tmHashMap.put(t,m);
    }
    public void invalidate(){
        tmHashMap.clear();
    }
    public <T>int size(){
        return tmHashMap.size();
    }
    public T getKey(){
        return this.key;
    }
}
class MergeUtility<T>{

    public <T> List<T> merge(List<T> firstList,List<T> secondList){
        List<T> newList=new ArrayList<>();
        for(T t:firstList){
            newList.add(t);
        }
        for (T t:secondList){
            newList.add(t);
        }
        return newList;
    }
    public <T> List<T> intersection(List<T> firstList,List<T> secondList){
        HashSet<T> newList=new HashSet<>();
        for (T t1:firstList){
            for (T t2:firstList){
                if(t1.equals(t2))newList.add(t1);
            }
        }
        return new ArrayList<>(newList);
    }
    public <T> List<T> difference (List<T> firstList,List<T> secondList) {
        List<T> newList=firstList.stream().filter(x->!firstList.contains(x)).toList();
        for (T t1:secondList){
           if (!firstList.contains(t1))newList.add(t1);
        }
        return newList;
    }

}




class Pair2<T,S>{
    private T t;
    private S s;

    public Pair2(T t,S s){
        this.t=t;
        this.s=s;
    }

    public T getT() {
        return t;
    }

    public S getS() {
        return s;
    }

    public void setT(T t) {
        this.t = t;
    }

    public void setS(S s) {
        this.s = s;
    }
}
class Pair<T,S>{
    private T first;
    private S second;
    public Pair(T first,S second){
        this.first=first;
        this.second=second;

    }
    public T getFirst(){
        return this.first;

    }
    public S getSecond(){
        return this.second;

    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
class Box<T> {
    private T t;
    public void set(T t){
        this.t=t;
    }
    public T getT() {
        return t;
    }

    @Override
    public String toString() {
        return "Box{" +
                "t=" + t +
                '}';
    }
}