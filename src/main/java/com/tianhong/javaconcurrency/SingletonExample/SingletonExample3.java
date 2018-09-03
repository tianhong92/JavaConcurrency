package com.tianhong.javaconcurrency.SingletonExample;

import com.tianhong.javaconcurrency.annotations.Recommend;
import com.tianhong.javaconcurrency.annotations.ThreadSafe;

/**
 *  枚举模式： 最安全
 */
@ThreadSafe
@Recommend
public class SingletonExample3 {
    private SingletonExample3(){ }
    public static SingletonExample3 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }
    private enum Singleton {
        INSTANCE;
        private SingletonExample3 singletonExample;

        // JVM 保证这个方法绝对只调用一次， 在枚举类调用之前初始化的
        Singleton(){
            singletonExample = new SingletonExample3();
        }
        public SingletonExample3 getInstance(){
            return singletonExample;
        }
    }
}
