package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Memory {
    public static void main(String[] args) {
        List<More>mores=new ArrayList<>();
        int i=0;
        while (i<100) {
            More more = new More();
            mores.add(more);
            i++;
            more = null;
        }
        System.out.println("");


    }
}
class More{
    public static int num;
    private int id;
    public More(){
        this.id=num++;
    }
}
