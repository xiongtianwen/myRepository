package com.example.demo.factory.func;

import com.example.demo.factory.cars.Aodi;
import com.example.demo.factory.cars.Cars;

public class AodiFactory implements CarsFactory {
    @Override
    public Cars getCars() {
        return new Aodi();
    }
}
