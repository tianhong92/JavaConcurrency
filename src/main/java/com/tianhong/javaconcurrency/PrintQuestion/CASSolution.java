package com.tianhong.javaconcurrency.PrintQuestion;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// 忙等不好

public class CASSolution implements  Runnable {
    private static AtomicInteger currentCount = new AtomicInteger(0);

    private static final Integer MAX_COUNT = 30;

    private static String[] chars = {"a", "b", "c"};

    private String name;

    public CASSolution(String name){
        this.name = name;
    }

    @Override
    public void run() {
        while(currentCount.get() < MAX_COUNT){
            if(this.name.equals(chars[currentCount.get()%3])){
                printAndPlusOne(this.name + "\t" + currentCount);
            }
        }
    }

    public void printAndPlusOne(String content){
        System.out.println(content);
        currentCount.getAndIncrement();
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 20, TimeUnit.MINUTES, new LinkedBlockingDeque<>());
        executor.execute(new CASSolution("a"));
        executor.execute(new CASSolution("b"));
        executor.execute(new CASSolution("c"));
        executor.shutdown();
    }
}