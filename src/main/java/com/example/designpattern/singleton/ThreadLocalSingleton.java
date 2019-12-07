package com.example.designpattern.singleton;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal 实现单例
 * 同一个线程拿到的是同一个对象,这个也是 ThreadLocal 的特点
 * 线程隔离
 */
public class ThreadLocalSingleton {

    private ThreadLocalSingleton () {}

    private static final ThreadLocal<ThreadLocalSingleton> threadLocal  = new ThreadLocal<ThreadLocalSingleton>() {
        @Override
        protected ThreadLocalSingleton initialValue() {//初始化值
            return new ThreadLocalSingleton();
        }
    };
    public static ThreadLocalSingleton getInstance() {
        return threadLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
//        for (int i=0;i<100; i++) {
//            new Thread(()->{
//                ThreadLocalSingleton instance = ThreadLocalSingleton.getInstance();
//                System.out.println(Thread.currentThread().getName() + ":" + instance);
//            }).start();
//        }
//        ThreadLocalSingleton instance = ThreadLocalSingleton.getInstance();
//        ThreadLocalSingleton instance2 = ThreadLocalSingleton.getInstance();
//
//        System.out.println(instance);
//        System.out.println(instance2);
//
//        new Thread(()->{
//            ThreadLocalSingleton instance4 = ThreadLocalSingleton.getInstance();
//                System.out.println(Thread.currentThread().getName() + ":" + instance4);
//        }).start();
        test();
    }

    public static void test() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0 ;i<10; i++) {
            executorService.submit(()->{
                countDownLatch.countDown();
                try {
                    TimeUnit.SECONDS.sleep(0);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }

                ThreadLocalSingleton instance = ThreadLocalSingleton.getInstance();
                System.out.println(Thread.currentThread().getName() + "___" + instance);
            });
        }

        countDownLatch.await();
    }



}
