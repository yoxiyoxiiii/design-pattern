package com.example.designpattern.factory.abstractfactorymethod;

import com.example.designpattern.factory.BMWCar;

/**
 * 宝马车系
 */
public class BMWFactoryMethod {

    public BMWCarX1 X1() {
        return new BMWCarX1();
    }

    public BMWCarX5 X5() {
        return new BMWCarX5();
    }
}
