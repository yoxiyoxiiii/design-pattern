package com.example.designpattern.factory.abstractfactorymethod;

public class AODIFactoryMethod {

    public AODICarA3 aodiCarA3() {
        return new AODICarA3();
    }

    public AODICarA4L aodiCarA4L() {
        return new AODICarA4L();
    }
}
