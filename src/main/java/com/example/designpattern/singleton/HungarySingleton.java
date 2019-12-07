package com.example.designpattern.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 解决线程安全问题
 * 简单粗暴，饿汉式
 * 不推荐
 */
public class HungarySingleton implements Serializable {

    //类加载时进行初始化
    private static final  HungarySingleton instance = new HungarySingleton();

    // 构造器初始化
    private HungarySingleton() {
        if (instance!=null) {
            throw new RuntimeException("请不要重复创建对象");
        }
    }

    // 全局访问点
    public static HungarySingleton getInstance() {
        return instance;
    }


    public static void test() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0 ;i<10; i++) {
            executorService.submit(()->{
                countDownLatch.countDown();
                HungarySingleton instance = HungarySingleton.getInstance();
                System.out.println(Thread.currentThread().getName() + "___" + instance);
            });
        }

        countDownLatch.await();
    }


    public static void main(String[] args) throws Exception{
//              test2();
              test3();
    }


    /**
     * 反射破坏
     * @throws Exception
     */
    public static void test2() throws Exception {
        //得到默认构造器
        Constructor<HungarySingleton> declaredConstructor = HungarySingleton.class.getDeclaredConstructor();
        //强制访问
        declaredConstructor.setAccessible(true);
        // 创建对象
        HungarySingleton hungarySingleton = declaredConstructor.newInstance();
        System.out.println(hungarySingleton);
        HungarySingleton instance = HungarySingleton.getInstance();
        System.out.println(instance);
    }

    /**
     * 序列化和反序列化破坏单例
     * */
    public static void test3() throws IOException, ClassNotFoundException {

        //将对象写到磁盘
        HungarySingleton hungarySingleton = HungarySingleton.getInstance();
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("hungarySingleton.obj"));
        outputStream.writeObject(hungarySingleton);

        System.out.println("饿汉式=" +hungarySingleton);

        //然后再将对象从磁盘读取出来
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("hungarySingleton.obj"));
        HungarySingleton object = (HungarySingleton)inputStream.readObject();

        System.out.println("序列化和反序列化=" + object);
    }


    /**
     * 防止序列化和反序列化对单例的破坏
     * @return
     */
    public Object readResolve() {
        return instance;
    }

}
