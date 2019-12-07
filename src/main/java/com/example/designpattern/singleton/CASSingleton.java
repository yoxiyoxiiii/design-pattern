package com.example.designpattern.singleton;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * CSA 实现单例
 */
public class CASSingleton {

    private CASSingleton() {
    }

    private final static AtomicReference<CASSingleton> atomicReference = new AtomicReference<>();

    public static CASSingleton getInstance() {

        for (; ; ) {//自旋
            CASSingleton casSingleton = atomicReference.get();
            if (casSingleton != null) {
                return casSingleton;
            }
            casSingleton = new CASSingleton();
            // CSA 操作：如果当前的值是 null，就更新 为 casSingleton
            boolean compareAndSet = atomicReference.compareAndSet(null, casSingleton);
            if (compareAndSet) {
                return casSingleton;
            }
        }

    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CASSingleton instance = CASSingleton.getInstance();
                System.out.println(Thread.currentThread().getName() + ":" + instance);
            }).start();
        }
        CASSingleton instance = CASSingleton.getInstance();
        CASSingleton instance2 = CASSingleton.getInstance();
        CASSingleton instance3 = CASSingleton.getInstance();
        CASSingleton instance4 = CASSingleton.getInstance();

        System.out.println(instance);
        System.out.println(instance2);
        System.out.println(instance3);
        System.out.println(instance4);
    }
}
