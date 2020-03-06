package com.example.designpattern.factory;

public class YLIPay implements IPay {
    @Override
    public void pay() {
        System.out.println("银联支付");
    }
}
