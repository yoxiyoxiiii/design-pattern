package com.example.designpattern.factory.abstractfactorymethod;

import com.example.designpattern.factory.Car;

public class BMWCarX1 implements Car {

    public BMWCarX1() {
        System.out.println("X1");
    }

    @Override
    public void drive() {
        System.out.println("drive X1");
    }
}
