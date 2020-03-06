package com.example.designpattern.factory;

import com.example.designpattern.IPayFactory;

public class PayFactory implements IPayFactory {


    @Override
    public WXIPay createWXPay() {
        return new WXIPay();
    }

    @Override
    public ZFBIPay createZFBPay() {
        return new ZFBIPay();
    }

    @Override
    public YLIPay createYLIPay() {
        return new YLIPay();
    }
}
