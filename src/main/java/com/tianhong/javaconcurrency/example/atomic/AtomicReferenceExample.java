package com.tianhong.javaconcurrency.example.atomic;

import com.tianhong.javaconcurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ThreadSafe
public class AtomicReferenceExample {
    private static AtomicReference<Integer> count = new AtomicReference(0);
    public static void main(String[] args) {
        count.compareAndSet(0, 2);
        count.compareAndSet(2, 4);
        log.info("count: {}", count.get());
    }
}
