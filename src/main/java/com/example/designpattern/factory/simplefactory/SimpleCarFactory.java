package com.example.designpattern.factory.simplefactory;

import com.example.designpattern.factory.AODICar;
import com.example.designpattern.factory.BMWCar;
import com.example.designpattern.factory.Car;
import com.example.designpattern.factory.WLHGCar;

/**
 * 简单工厂，生产汽车
 */
public class SimpleCarFactory {

    public Car createCar(String carName) {
        Car car = null;
        if ("BMW".equals(carName)) {//宝马汽车
            car = new BMWCar();
        }
        if ("AODI".equals(carName)) {// 奥迪汽车
            car = new AODICar();
        }
        if ("WLHG".equals(carName)) {// 五菱宏光
            car = new WLHGCar();
        }
       // add if()........
       // add if()........
       // add if()........
        return car;
    }
}
