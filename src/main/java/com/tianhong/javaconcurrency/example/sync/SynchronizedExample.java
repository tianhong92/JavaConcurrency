package com.tianhong.javaconcurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample {
    //修饰代码块
    public void test1(){
        synchronized (this.getClass()){
            for(int i = 0; i < 10; i++){
                log.info("test1-{}", i);
            }
        }
    }

    //修饰一个方法
    synchronized public static void test2(){
        for(int i = 0; i < 10; i++){
            log.info("test2 - {}", i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample example = new SynchronizedExample();
        SynchronizedExample example2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example.test1();
        });
        executorService.execute(() -> {
            example2.test2();
        });
    }
}
