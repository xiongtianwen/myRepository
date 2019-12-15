package com.example.demo.factory.func;

public class funFactoryTest {
    public static void main(String[] args) {
        CarsFactory carsFactory = new BaomaFactory();
        CarsFactory carsFactory1 = new BenchiFactory();
        System.out.println(carsFactory.getCars().getName());
        System.out.println(carsFactory1.getCars().getName());
    }
}
