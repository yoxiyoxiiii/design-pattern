package com.example.designpattern.singleton;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 对饿汉式的 优化--> 双重检查锁
 *
 */
public class DoubleCheckSingleton {


    private DoubleCheckSingleton() { }



    /**
     * @see  SimpleSingleton 是懒加载，但是存在线程安全问题.
     * @see  HungarySingleton 饿汉式不够优雅，比如：我们想在使用对象的时候才去初始化，显然饿汉式不够优雅，太low
     * 于是我们就想到了，只要在懒加载的基础上解决线程安全问题就ok
     * 于是我们会想到 使用线程同步。
     * 我们首先来看一下 最简单的同步方式，静态方法的同步.
     * 下面的代码能解决我们的问题，但是我们知道，单例模式只会初始化一次，以后我们就只接用就好了，
     * 那么下面的代码就存在性能上的问题：
     *  1：下面两种写法效果一样，都是类级别的锁。虽然解决了线程安全问题，保证了在初始化的时候只有一个线程能执行，
     *  2：但是在后续的使用中其他线程访问的时候同样会有锁竞争，存在资源浪费的问题，这里就不太优雅了。
     * @return
     */
    public static synchronized DoubleCheckSingleton getInstance() {
        if (singleton == null ) {
            singleton = new DoubleCheckSingleton();
        }
        return singleton;
    }
    public static DoubleCheckSingleton getInstance2() {
        synchronized (DoubleCheckSingleton.class) {
            if (singleton == null ) {
                singleton = new DoubleCheckSingleton();
            }
        }
        return singleton;
    }


    /**
     * 进一步优化
     * @see DoubleCheckSingleton#getInstance()
     * @see DoubleCheckSingleton#getInstance2() 这两种都存在资源浪费问题
     * 为优化上面两种写法，于是可以写成下面这样的方式，
     * 粗略一看好像没问题，其实仔细分析一下是有问题的。
     * @return
     */
    public static DoubleCheckSingleton getInstance3() {
        if (singleton == null) {//如果两个线程 同时到达这里 ，这是 拿到的 singleton 都是 null
            synchronized (DoubleCheckSingleton.class) {// 然后 其中一个线程执行了初始化，结束了，剩下一个 也会再执行一次，所以也会有线程安全问题
                singleton = new DoubleCheckSingleton();
            }
        }
        return singleton;
    }

    /**
     * @see DoubleCheckSingleton#getInstance3() 为解决这里的问题，于是就有了下面的写法
     * @return
     * 下面的写法还有一个问题，在并发量较高的环境下也可能有线程安全问题.
     * 原因是 singleton = new DoubleCheckSingleton(); 这句话中 在JVM 执行的时候 并不是 原子的，分了几条指令完成操作
     * 比如：分配内存空间—>初始化对象->赋值地址值给成员变量；
     * 所以为解决这个问题我们需要在 singleton 使用 volatile 关键字
     * volatile ：保证 原子性和可见性
     */
    private static volatile DoubleCheckSingleton singleton;
    public static DoubleCheckSingleton doubleCheckSingleton() {
        if (singleton == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (singleton == null ) {//为了防止 两个线程都进了 第一个if导致的线程安全问题，所以可以再加一次判断
                    singleton = new DoubleCheckSingleton();
                }
            }
        }
        return singleton;
    }


    public static void test() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0 ;i<10; i++) {
            executorService.submit(()->{
                countDownLatch.countDown();
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }

                DoubleCheckSingleton instance = DoubleCheckSingleton.getInstance3();
                System.out.println(Thread.currentThread().getName() + "___" + instance);
            });
        }

        countDownLatch.await();
    }

    public static void main(String[] args) throws InterruptedException {
        test();
    }
}
