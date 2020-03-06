package com.example.designpattern.factory;

public class ZFBIPay implements IPay{
    @Override
    public void pay() {
        System.out.printf("支付宝");
    }
}
