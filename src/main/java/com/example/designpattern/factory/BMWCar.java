package com.example.designpattern.factory;

public class BMWCar implements Car{
    @Override
    public void drive() {
        System.out.println("驾驶 BMW  ！");
    }
}
