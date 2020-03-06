package com.example.designpattern.clone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 浅克隆
 * 克隆对象的基本数据类型数据，引用对象 复制 内存地址值, 改变引用对象值，都会被影响
 */
public class SimpleClone implements Cloneable{

    private String name;
    private List<User> users;

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public SimpleClone(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    @Override
    public String toString() {
        return "SimpleClone{" +
                "name='" + name + '\'' +
                ", users=" + users +
                '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        List<User> users = new ArrayList<>();
        users.add(new User("user1"));
        SimpleClone simpleClone = new SimpleClone("测试",users);
        // 克隆
        SimpleClone clone = (SimpleClone)simpleClone.clone();

        System.out.println("原始对象："+simpleClone.toString());
        System.out.println("克隆对象:"+ clone.toString());

        simpleClone.setName("修改name");
        simpleClone.getUsers().add(new User("user2"));

        System.out.println("修改后的原始对象："+ simpleClone.toString());
        System.out.println("修改后的克隆对象："+ clone.toString());

    }


}
