package com.example.designpattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 静态内部类实现代理模式
 * 利用静态加载的特性来保证 线程安全
 * 外部类加载的时候 不会立即加载静态内部类
 * 当 调用的时候 才会加载 静态内部类
 */
public class InnerStaticSingleton {

    private InnerStaticSingleton() {}

    //但外部调用 SingletonHolder.innerStaticSingleton 时才会加载这里的静态内部类
     private static class SingletonHolder{
        private final static InnerStaticSingleton innerStaticSingleton = new InnerStaticSingleton();

     }

     public static InnerStaticSingleton getInstance() {
        return SingletonHolder.innerStaticSingleton;
     }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        InnerStaticSingleton instance = InnerStaticSingleton.getInstance();
//        InnerStaticSingleton instance2 = InnerStaticSingleton.getInstance();
//        InnerStaticSingleton instance3 = InnerStaticSingleton.getInstance();
//        InnerStaticSingleton instance4 = InnerStaticSingleton.getInstance();
//
//        System.out.println(instance);
//        System.out.println(instance2);
//        System.out.println(instance3);
//        System.out.println(instance4);
//
//        for (int i=0;i<=100; i++) {
//            new Thread(()->{
//                InnerStaticSingleton singleton = InnerStaticSingleton.getInstance();
//                System.out.println(Thread.currentThread().getName()+"__"+ singleton);
//            }).start();
//        }
        test3();
    }

    public static void test3() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<InnerStaticSingleton> declaredConstructor = InnerStaticSingleton.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        InnerStaticSingleton innerStaticSingleton = declaredConstructor.newInstance();
        System.out.println(innerStaticSingleton);

        InnerStaticSingleton instance = InnerStaticSingleton.getInstance();
        System.out.println(instance);
    }


}
