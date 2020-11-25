package org.qifei;

import org.junit.Test;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class SingleTon {

    @Test
    public void test() {
        SingleTon1 s = SingleTon1.getSingleTon();
    }
}

//饿汉式
class SingleTon1{
    private  static SingleTon1 singleTon1 = new SingleTon1();
    private SingleTon1(){

    }
    public static SingleTon1 getSingleTon(){
        return singleTon1;
    }
}

class Singleton2 {

    /**
     * 定义一个变量来存储创建好的类实例
     */

    private static Singleton2 uniqueInstance = null;

    /**
     * 私有化构造方法，好在内部控制创建实例的数目
     */

    private Singleton2(){
    }

    /**
     * 定义一个方法来为客户端提供类实例
     * @return 一个Singleton的实例
     */

    public static synchronized Singleton2 getInstance(){

        //判断存储实例的变量是否有值
        if(uniqueInstance == null){
            //如果没有，就创建一个类实例，并把值赋值给存储类实例的变量
            uniqueInstance = new Singleton2();
        }

        //如果有值，那就直接使用
        return uniqueInstance;
    }

}

class Singleton {

    /**
     * 对保存实例的变量添加volatile的修饰
     */

    private volatile static Singleton instance = null;

    private Singleton(){

    }

    public static Singleton getInstance(){

//先检查实例是否存在，如果不存在才进入下面的同步块

        if(instance == null){

//同步块，线程安全的创建实例

            synchronized(Singleton.class){

//再次检查实例是否存在，如果不存在才真的创建实例

                if(instance == null){

                    instance = new Singleton();

                }

            }

        }

        return instance;

    }

}