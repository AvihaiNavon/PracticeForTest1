package org.example;

import java.util.*;
import java.util.stream.IntStream;

public class MessPark { // תרגיל תהליכונים
    public static void main(String[] args) {

        System.out.println("Hello world!");
        new MessPark();
    }
    private List<Playground>playgrounds;
    private  List<Visitor> visitors;
    private Thread games;
    public MessPark(){
        Random random=new Random();
        playgrounds=new ArrayList<>();
        IntStream.range(1,6).forEach(item->playgrounds.add(new Playground(item,("playground"+" "+ item))));
        visitors=new ArrayList<>();
        IntStream.range(1,51).forEach(item->visitors.add(new Visitor(item,"Visitor"+" "+ item)));
        games= new Thread(()->{
            while (true){
                for (Visitor visitor:visitors){
                    if(visitor.isFree()){
                        int visitorPriority=visitor.getPriority();
                        for(Playground playground:playgrounds){
                            if(playground.getIdPlayground()==visitorPriority){
                                if(playground.addToListPlay(visitor)) {
                                    visitor.setActivePlayground(visitorPriority);
                                    visitor.setCountActivePlayground(1);
                                    visitor.setFree(false);
                                    break;
                                }
                                }else {
                                    visitorPriority=random.nextInt(1,5);
                                }
                            }
                        }
                    }
                }
        });
        games.start();
        statusPark();
        endGames();
    }
    public void statusPark(){
        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(5000);
                 System.out.println(playgrounds);
                 System.out.println(visitors);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }
    public void endGames(){
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        games.stop();
        System.out.println(playgrounds);
        System.out.println(visitors);
    }


}
class Playground { //מתקן משחק
    private int idPlayground;
    private String namePlayground;
    private int timeActive; // זמן הפעלת המתקן
    private int capacity; // קיבולת עלייה למתקן
    private int capacityRound;
    private int sumVisitorAllDay;
    private List<Visitor> playNow;



    public Playground(int id, String name){
        sumVisitorAllDay=0;
        playNow=new ArrayList<>();
        Random random=new Random();
        this.idPlayground=id;
        this.namePlayground=name;
        this.timeActive= random.nextInt(10000,30000);
        this.capacity=random.nextInt(7);
        new Thread(()->{
            while (true){
                        work();
                        sumVisitorAllDay += playNow.size();
                        capacityRound = playNow.size();
                        for (Visitor visitor : playNow) {
                            visitor.setFree(true);
                        }
                        playNow.clear();
                        rest();

            }
        }).start();
    }
    public void rest(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void work(){
        try {
            Thread.sleep(timeActive);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean addToListPlay(Visitor visitor){
        boolean check=false;
        if(playNow.size()<capacity){
            this.playNow.add(visitor);
            check=true;
        }else check=false;
        return check;
    }




    public int getIdPlayground() {
        return idPlayground;
    }

    public String getNamePlayground() {
        return namePlayground;
    }
    public int getSumVisitorAllDay() {
        return sumVisitorAllDay;
    }

    public int getTimeActive() {
        return timeActive;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Playground{" +
                ", namePlayground='" + namePlayground + '\'' +
                ", capacityRound=" + capacityRound +
                ", sumVisitorAllDay=" + sumVisitorAllDay +
                '}';
    }
}
class Visitor{ // מבקר
    private  int id;
    private String name;
    private int stoodTimeLine;
    private int SpentTimePlayground;
    private int activePlayground;
    private int countActivePlayground;
    private  boolean free;
    private HashMap<Integer,Integer> priorityMap;
    public Visitor(int id,String name){
        Random random=new Random();
        this.priorityMap=new HashMap<>();
        this.id=id;
        this.name=name;
        free=true;
        int priority=100;
        int [] priorityVisitor=new int[5];
        for(int i=0;i<priorityVisitor.length;i++){
            if(priority>0){
                priorityVisitor[i]=priority-random.nextInt(0,priority);
                priority-=priorityVisitor[i];
                if(i==priorityVisitor.length-1){
                    priorityVisitor[i]+=priority;
                }
            }else priorityVisitor[i]=0;
        }
        IntStream.range(1,6).forEach(item-> priorityMap.put(item,priorityVisitor[item-1]));

        new Thread(()->{
            while (true){
                    if(free){
                        try {
                            Thread.sleep(1000);
                            stoodTimeLine++;
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }else {
                        try {
                            Thread.sleep(1000);
                            SpentTimePlayground++;
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }).start();

    }
    public int getPriority(){
        int maxValue=0;
        int keyValue=0;
        for (int key:priorityMap.keySet()){
            if(maxValue<priorityMap.get(key)){
                maxValue=priorityMap.get(key);
                keyValue=key;
            }
        }
        return keyValue;
    }

    public void setActivePlayground(int activePlayground) {
        this.activePlayground = activePlayground;
    }

    public void setCountActivePlayground(int countActivePlayground) {
        this.countActivePlayground += countActivePlayground;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStoodTimeLine() {
        return stoodTimeLine;
    }

    public int getSpentTimePlayground() {
        return SpentTimePlayground;
    }

    public int getActivePlayground() {
        return activePlayground;
    }

    public int getCountActivePlayground() {
        return countActivePlayground;
    }

    public boolean isFree() {
        return free;
    }

    public HashMap<Integer, Integer> getPriorityMap() {
        return priorityMap;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stoodTimeLine=" + stoodTimeLine +
                ", SpentTimePlayground=" + SpentTimePlayground +
                ", activePlayground=" + activePlayground +
                ", countActivePlayground=" + countActivePlayground +
                ", free=" + free +
                ", priorityMap=" + priorityMap +
                '}';
    }
}
class Utils {

}



