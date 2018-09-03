package com.tianhong.javaconcurrency.example.atomic;

import com.tianhong.javaconcurrency.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@ThreadSafe
@Slf4j
public class AtomicFieldUpdaterExample {
    private static AtomicIntegerFieldUpdater<AtomicFieldUpdaterExample> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicFieldUpdaterExample.class, "count");
    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {
        AtomicFieldUpdaterExample example = new AtomicFieldUpdaterExample();
        if(updater.compareAndSet(example, 100, 120)) {
            log.info("update success 1, {}", example.getCount());
        }
        if(updater.compareAndSet(example, 100, 120)){
            log.info("update success 2, {}", example.getCount());
        } else {
            log.info("update fail, {}", example.getCount());
        }
    }
}
