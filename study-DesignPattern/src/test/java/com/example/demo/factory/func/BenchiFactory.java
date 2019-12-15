package com.example.demo.factory.func;

import com.example.demo.factory.cars.Benchi;
import com.example.demo.factory.cars.Cars;

public class BenchiFactory implements CarsFactory {
    @Override
    public Cars getCars() {
        return new Benchi();
    }
}
