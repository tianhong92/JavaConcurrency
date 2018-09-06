package com.tianhong.javaconcurrency.example.syncContainer;

import java.util.Iterator;
import java.util.Vector;

public class TraverseIssue {
    private static void test1(Vector<Integer> v1){
        for(Integer i : v1){
            if(i.equals(3)){
                v1.remove(i);
            }
        }
    }

    private static void test2(Vector<Integer> v2){
        Iterator<Integer> iterator = v2.iterator();
        while(iterator.hasNext()){
            Integer i = iterator.next();
            if(i.equals(3)){
                v2.remove(3);
            }
        }
    }

    private static void test3(Vector<Integer> v3){
        for(int i = 0; i < v3.size(); i++){
            if(v3.get(i).equals(3)){
                v3.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test2(vector);
//        test2(vector);
//        test3(vector);
    }
}
