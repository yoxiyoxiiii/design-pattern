package com.example.designpattern;

import com.example.designpattern.factory.WXIPay;
import com.example.designpattern.factory.YLIPay;
import com.example.designpattern.factory.ZFBIPay;

public interface IPayFactory {

    WXIPay createWXPay();

    ZFBIPay createZFBPay();

    YLIPay createYLIPay();
}
