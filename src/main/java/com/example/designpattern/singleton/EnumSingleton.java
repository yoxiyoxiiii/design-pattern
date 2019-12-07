package com.example.designpattern.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 枚举单例
 */
public enum EnumSingleton {

    INSTANCE;

    public static void main(String[] args) throws Exception {
//      test();
      test2();
    }

    public static void test() throws Exception{
        Constructor<EnumSingleton> declaredConstructor = EnumSingleton.class.getDeclaredConstructor(String.class,int.class);
        declaredConstructor.setAccessible(true);
        EnumSingleton enumSingleton = declaredConstructor.newInstance("测试",007);
        System.out.println(enumSingleton);
    }
    public static void test2() throws Exception{
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("EnumSingleton.obj"));
        EnumSingleton instance = EnumSingleton.INSTANCE;
        System.out.println(instance);
        outputStream.writeObject(instance);
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("EnumSingleton.obj"));
        EnumSingleton enumSingleton =(EnumSingleton)objectInputStream.readObject();
        System.out.println(enumSingleton);
        System.out.println(EnumSingleton.INSTANCE.name());
    }
}
