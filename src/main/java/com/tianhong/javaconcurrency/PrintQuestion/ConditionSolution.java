package com.tianhong.javaconcurrency.PrintQuestion;

// 思路： 三个线程对应3个condition， 三个condition共享一个lock， 线程拿到锁之后， 通过state判断， 如果没有轮到则通过
// 当前线程对应的condition使当前线程进入等待状态， 并释放锁； 如果轮到， 更改state状态并通过下一个线程对应的condition唤醒下一个
// 线程；

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 效率： 线程不满足条件等待， 满足条件执行打印， 并唤醒下一个线程， 效率比较高
public class ConditionSolution {

    class PrintABCUsingCondition {
        private Lock lock = new ReentrantLock();
        private Condition conditionA = lock.newCondition();
        private Condition conditionB = lock.newCondition();
        private Condition conditionC = lock.newCondition();
        private int state = 0;

        public void printA() {
            System.out.println("A");
            print("A", 0, conditionA, conditionB);
        }

        public void printB() {
            System.out.println("B");
            print("B", 1, conditionB, conditionC);
        }

        public void printC() {
            System.out.println("C");
            print("C", 2, conditionC, conditionA);
        }

        private void print(String name, int currentState, Condition currentCondition, Condition nextCondition){
            for(int i = 0; i < 10;) {
                lock.lock();
                try {
                    while(state % 3 != currentState){
                        currentCondition.await();
                    }
                    System.out.println(Thread.currentThread().getName() + " print" + name);
                    System.out.println(Thread.activeCount());
                    state++;
                    i++;
                    nextCondition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        ConditionSolution cs = new ConditionSolution();
        PrintABCUsingCondition printABC = cs.new PrintABCUsingCondition();
        new Thread(() -> printABC.printA()).start();
        new Thread(() -> printABC.printB()).start();
        new Thread(() -> printABC.printC()).start();
    }
}
