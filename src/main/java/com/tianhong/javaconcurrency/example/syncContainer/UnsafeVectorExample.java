package com.tianhong.javaconcurrency.example.syncContainer;


import com.tianhong.javaconcurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Vector;

@Slf4j
@NotThreadSafe
// 使用同步容器也可能有线程安全问题
public class UnsafeVectorExample {
    private static List<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while(true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
    }
}
