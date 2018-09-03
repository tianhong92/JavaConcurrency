package com.tianhong.javaconcurrency.SingletonExample;

// 饿汉模式(单例实例在类装载的时候创建)
public class SingletonExample2 {
    private SingletonExample2(){ }

    public static SingletonExample2 instance = null;
    static{
        instance = new SingletonExample2();
    }
    public SingletonExample2 getInstance(){
        return instance;
    }
}
