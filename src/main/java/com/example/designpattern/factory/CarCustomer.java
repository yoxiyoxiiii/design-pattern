package com.example.designpattern.factory;

import com.example.designpattern.factory.simplefactory.SimpleCarFactory;

/**
 * 汽车消费者
 */
public class CarCustomer {
    private SaleCarStore saleCarStore ;
    CarCustomer(SaleCarStore saleCarStore) {//消费者通过4s 店买车
        this.saleCarStore = saleCarStore;
    }

    public Car getCar(String carName) {
        Car car = saleCarStore.sale(carName);
        return car;
    }

    /**
     * 驾驶 买完车之后就 自己开车走了
     * @param car
     */
    public void drive(Car car) {
        car.drive();
    }


    public static void main(String[] args) {
        SimpleCarFactory simpleCarFactory = new SimpleCarFactory();
        SaleCarStore car4sStore = new SaleCarStore(simpleCarFactory);
        CarCustomer carCustomer = new CarCustomer(car4sStore);
        Car bwm = carCustomer.getCar("WLHG");
        carCustomer.drive(bwm);
    }
}
