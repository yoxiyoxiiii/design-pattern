package com.example.designpattern.factory;

import com.example.designpattern.factory.simplefactory.SimpleCarFactory;

/**
 * 汽车销售方
 */
public class SaleCarStore {

    private SimpleCarFactory simpleCarFactory;
    SaleCarStore(SimpleCarFactory simpleCarFactory) {
        this.simpleCarFactory = simpleCarFactory;
    }

    public Car sale(String carName) {
//        Car car = null;
////      if ("BMW".equals(carName)) {//宝马汽车
////          car = new BMWCar();
////      }
////      if ("AODI".equals(carName)) {// 奥迪汽车
////          car = new AODICar();
////      }
////      // add
////      if ("WLHG".equals(carName)) {// 五菱宏光
////          car = new WLHGCar();
////      }
        // 简单工厂创建Car 对象
        Car car = simpleCarFactory.createCar(carName);
        return car;
    }
}
