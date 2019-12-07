package com.example.designpattern.singleton;

import java.util.concurrent.TimeUnit;

/**
 *单例：为了优化jvm 运行时效率，减少对象的频繁创建和回收。jvm 在运行期间整个内中只存在一个对象实例。
 * 实现方式：1. 构造器私有化，防止外部创建对象
 *          2. 提供全局访问点。
 * 下面是最简单实现方式，在多线程情况下存在线程安全问题。
 */
public class SimpleSingleton {


    //1.静态成员
    private static SimpleSingleton instance;

    //2. 构函数私有化
    private SimpleSingleton() {}

    //3. 提供全局访问方法
    final static Object object = new Object();
    public static  SimpleSingleton getInstance() {
            if (instance == null) {
                synchronized (SimpleSingleton.class) {
                    if (instance == null) {
                        instance = new SimpleSingleton();
                    }

                }
            }
            return instance;
    }

    //单线程测试
    public static void test() {
        SimpleSingleton instance = SimpleSingleton.getInstance();
        SimpleSingleton instance2 = SimpleSingleton.getInstance();
        SimpleSingleton instance3 = SimpleSingleton.getInstance();
        System.out.println(instance);
        System.out.println(instance2);
        System.out.println(instance3);
    }


    //多线程测试
    public static void test2() {
        for (int i=0 ;i<300000; i++) {
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SimpleSingleton instance = SimpleSingleton.getInstance();
                System.out.println(instance);
            }).start();
        }
    }

    public static void main(String[] args) {
//        test();
        test2();
    }
}
