package com.example.designpattern.factory.factorymethod;

import com.example.designpattern.factory.AODICar;
import com.example.designpattern.factory.BMWCar;
import com.example.designpattern.factory.WLHGCar;

public class CarFactoryMethod  {

    //生产宝马
    public BMWCar createBMWCar() {
        return new BMWCar();
    }

    // 生产奥迪
    public AODICar createAODICar() {
        return new AODICar();
    }

    //生产五菱宏光
    public WLHGCar createWLHGCar() {
        return new WLHGCar();
    }
}
