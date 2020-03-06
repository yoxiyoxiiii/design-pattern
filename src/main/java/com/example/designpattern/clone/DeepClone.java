package com.example.designpattern.clone;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 克隆出完全独立的 两个对象
 */
public class DeepClone implements Cloneable, Serializable {

    private String name;
    private User users;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeepClone(String name, User users) {
        this.name = name;
        this.users = users;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            return deepClone();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private DeepClone deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        DeepClone deepClone = (DeepClone) ois.readObject();
        return deepClone;
    }

    public static void main(String[] args) throws CloneNotSupportedException {

        DeepClone deepClone = new DeepClone("deepOld",new User("userOld"));
        DeepClone clone = (DeepClone) deepClone.clone();


        System.out.println("原始对象："+deepClone.toString());
        System.out.println("克隆对象:"+ clone.toString());

        deepClone.setName("deepNew");
        User users = deepClone.getUsers();
        users.setUsername("userNew");

        System.out.println("修改后的原始对象："+ deepClone.toString());
        System.out.println("修改后的克隆对象："+ clone.toString());

    }


    @Override
    public String toString() {
        return "DeepClone{" +
                "name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
