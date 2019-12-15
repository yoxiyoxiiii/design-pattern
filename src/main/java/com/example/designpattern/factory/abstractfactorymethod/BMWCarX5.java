package com.example.designpattern.factory.abstractfactorymethod;

import com.example.designpattern.factory.Car;

public class BMWCarX5 implements Car {

    public BMWCarX5() {
        System.out.println("X5");
    }

    @Override
    public void drive() {
        System.out.println("drive X5");
    }
}
