package com.example.designpattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class DesignpatternApplication {

    public static void main(String[] args) {
//        SpringApplication.run(DesignpatternApplication.class, args);

        try(InputStream in = new FileInputStream("")) {
            in.read();
    } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
