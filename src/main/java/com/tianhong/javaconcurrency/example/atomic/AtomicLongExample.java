package com.tianhong.javaconcurrency.example.atomic;

import com.tianhong.javaconcurrency.annotations.NotThreadSafe;
import com.tianhong.javaconcurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

//引入atomic包
@Slf4j
@ThreadSafe
public class AtomicLongExample {
    // 请求总数
    public static int clientTotal = 5000;
    // 同时并发执行的线程数
    public static int threadTotal = 200;
    public static LongAdder count = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        // 计数器闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0; i < clientTotal; i++){
            executorService.execute(() -> {
                try {
                    // 信号量模拟同时允许多少个线程执行， 达到一定并发数add会被阻塞
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("Exception", e);
                }
                // 执行完一次， 计数值减一
                countDownLatch.countDown();
            });
        }
        // 保证countdownLatch必须减为0， 这表明所有线程都执行完
        countDownLatch.await();
        executorService.shutdown();
        log.info("Count:{}", count);
    }
    private static void add(){
        count.increment();
    }
}
