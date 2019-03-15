package com.tianhong.javaconcurrency.PrintQuestion;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockSolution implements Runnable {
    private static Lock lock = new ReentrantLock();
    private static Integer currentCount = 0;
    private static final Integer MAX_COUNT = 30;
    private static String[] chars = {"a", "b", "c"};
    private String name;

    public LockSolution(String name){
        this.name = name;
    }

    @Override
    public void run(){
        while(currentCount < MAX_COUNT){
            //lock()与unlock()必须和try...finally配套使用 避免出现异常不解锁
            try{
                lock.lock();
                while(this.name.equals(chars[currentCount%3])&&currentCount<MAX_COUNT){
                    printAndPlusOne(this.name);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printAndPlusOne(String name){
        System.out.println(name + "\t" + currentCount + "\t" + Thread.activeCount());
        currentCount++;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 20, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>());
        executor.execute(new LockSolution("a"));
        executor.execute(new LockSolution("b"));
        executor.execute(new LockSolution("c"));
        executor.shutdown();
    }
}
