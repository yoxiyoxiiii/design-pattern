package com.example.designpattern.factory;

public class WXIPay implements IPay {
    @Override
    public void pay() {
        System.out.printf("微信支付");
    }
}
