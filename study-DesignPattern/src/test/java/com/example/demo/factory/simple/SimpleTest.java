package com.example.demo.factory.simple;

public class SimpleTest {

    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        System.out.println(simpleFactory.getCars("benchi").getName());
    }

}
