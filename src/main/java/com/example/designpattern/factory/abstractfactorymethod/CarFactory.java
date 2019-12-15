package com.example.designpattern.factory.abstractfactorymethod;

import com.example.designpattern.factory.Car;

/**
 * car 工厂定义
 */
public interface CarFactory {

    Car createCar(String type);
}
