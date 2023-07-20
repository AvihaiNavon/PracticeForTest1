package org.example;

public class Generics {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        new Generics();
    }
    public Generics(){
        Pair<Integer,String>pair=new Pair<>(1,"avihai");
        System.out.println(pair);
        System.out.println(swap(pair));

    }
    public Pair swap (Pair pair){
        Pair pairSwap=new Pair(pair.getSecond(),pair.getFirst());
        return pairSwap;
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
