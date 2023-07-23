package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class StreamPractice {
    public static void main(String[] args) {

        List<List<String>> list=Arrays.asList(Arrays.asList("B","A") ,Arrays.asList("C","D"));
        List<String>ownList=list.stream().flatMap(x->x.stream()).toList();// לשטח את המפה
        List<String>sortedList= ownList.stream().sorted(Comparator.reverseOrder()).toList();// להחזיר ברוורס
        System.out.println(sortedList);

    }

}
