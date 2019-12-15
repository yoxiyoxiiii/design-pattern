package com.example.designpattern.factory.abstractfactorymethod;

import com.example.designpattern.factory.Car;

public class BMWCarFactoryMethod implements CarFactory {
    @Override
    public Car createCar(String type) {
        switch (type) {
            case "X1":
                return bmwCarX1();
            case "X5":
                return bmwCarX5();
        }
        return null;
    }

    private BMWCarX1 bmwCarX1() {
        return new BMWCarX1();
    }
    private BMWCarX5 bmwCarX5() {
        return new BMWCarX5();
    }
}
