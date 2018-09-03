package com.tianhong.javaconcurrency.SingletonExample;

import com.tianhong.javaconcurrency.annotations.ThreadSafe;

// 懒汉模式(单例实例在第一次使用的时候创建)
// 可以使用双重锁单例模式
@ThreadSafe
public class SingletonExample1 {
    // 单例模式， 构造函数为private
    private SingletonExample1() { }
    // 单例对象
    private volatile static SingletonExample1 instance = null;

    // 1. memory = allocate() 分配对象内存空间
    // 2. ctorInstance() 初始化对象
    // 3. instance = memory 设置instance指向刚分配的内存
    // JVM和cpu优化， 发生了指令重拍
    // 1. memory = allocate() 分配对象内存空间
    // 3. instance = memory 设置instance指向刚分配的内存
    // 2. ctorInstance() 初始化对象
    // 线程A执行到3， 线程B会以为instance已经创建好了， 实际上还没有初始化
    // 所以要加volatile

    // 静态的工厂方法
    public static SingletonExample1 getInstance(){
        if(instance == null){ //双重检测机制
            synchronized (SingletonExample2.class) {
                if (instance == null) {
                    instance = new SingletonExample1();
                }
            }
        }
        return instance;
    }
}
