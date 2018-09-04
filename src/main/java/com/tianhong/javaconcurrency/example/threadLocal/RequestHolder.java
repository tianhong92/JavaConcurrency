package com.tianhong.javaconcurrency.example.threadLocal;

public class RequestHolder {
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();
    public static void add(Long id){
        //默认map中key为线程本地对象， value = id;
        requestHolder.set(id);
    }

    public static Long getId(){
        //默认传入当前线程的id， 得到我们set的id值
        return requestHolder.get();
    }

    public static void remove(){
        //防止内存泄漏
        requestHolder.remove();
    }
}
