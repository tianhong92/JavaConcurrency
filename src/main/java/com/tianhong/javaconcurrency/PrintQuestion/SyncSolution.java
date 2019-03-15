package com.tianhong.javaconcurrency.PrintQuestion;

// 三个线程轮流打"a", "b", "c";

public class SyncSolution implements Runnable {

    private static String[] chars = {"a", "b", "c"};
    private static final OperateInteger operateInteger = new OperateInteger(0, 30);
    private String name;

    public SyncSolution(String name){
        this.name = name;
    }

    @Override
    public void run() {
        while(operateInteger.getCurrentCount() < operateInteger.getMaxCount()){
            synchronized (operateInteger){
                if(operateInteger.getCurrentCount() < operateInteger.getMaxCount()){
                    if(this.name.equals(chars[operateInteger.getCurrentCount() % 3])){
                        operateInteger.printAndPlusOne();
                        operateInteger.notifyAll();
                    } else {
                        try {
                            operateInteger.wait();
                            System.out.println(Thread.activeCount());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread ta = new Thread(new SyncSolution("a"));
        Thread tb = new Thread(new SyncSolution("b"));
        Thread tc = new Thread(new SyncSolution("c"));
        ta.start();
        tb.start();
        tc.start();
    }

    private static class OperateInteger {
        private int currentCount;
        private int maxCount;

        private void printAndPlusOne(){
            System.out.println(chars[currentCount%3] + "\t" + currentCount + "\t" + Thread.activeCount());
            currentCount++;
        }

        public OperateInteger(int currentCount, int maxCount){
            this.currentCount = currentCount;
            this.maxCount = maxCount;
        }

        public int getCurrentCount() {
            return currentCount;
        }

        public int getMaxCount() {
            return maxCount;
        }
    }
}
