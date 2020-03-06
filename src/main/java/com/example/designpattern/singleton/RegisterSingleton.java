package com.example.designpattern.singleton;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册式单例
 */
public class RegisterSingleton {

    private static Map<String,RegisterSingleton> map = new ConcurrentHashMap<>(1);

    private RegisterSingleton() {}
    private static volatile RegisterSingleton instance;
    public static RegisterSingleton getInstance() {
        instance = map.get("instance");
       if (null == instance) {
           synchronized (RegisterSingleton.class) {
               instance = map.get("instance");
               if (null == instance) {
                   instance = new RegisterSingleton();
                   map.put("instance",instance);
               }
           }
       }
       return instance;
    }

    public static void main(String[] args) {
        for (int i=0;i<100; i++) {
            new Thread(()->{
                RegisterSingleton instance = RegisterSingleton.getInstance();
                System.out.println(instance);
            }).start();
        }
    }
}
