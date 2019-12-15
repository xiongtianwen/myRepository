package com.example.demo.factory.func;

import com.example.demo.factory.cars.Baoma;
import com.example.demo.factory.cars.Cars;

public class BaomaFactory implements CarsFactory{
    @Override
    public Cars getCars() {
        return new Baoma();
    }
}
