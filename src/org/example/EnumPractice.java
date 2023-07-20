package org.example;

public class EnumPractice {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        new EnumPractice();
    }
    public EnumPractice(){
        meeting();
    }
    public void meeting(){
        Day day;
        day=Day.FRIDAY;
        System.out.println(day);
        if (day!=Day.MONDAY)System.out.println("OK");
        switch (day){
            case FRIDAY ->System.out.println("FRIDAY");
            case MONDAY -> System.out.println("MONDAY");
        }

    }

}
enum Day{ // קבוע עם ערכים קבועים בשמות קבועים
    SUNDAY,MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY
}
