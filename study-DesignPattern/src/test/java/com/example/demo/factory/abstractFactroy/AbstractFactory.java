package com.example.demo.factory.abstractFactroy;

import com.example.demo.factory.cars.Cars;
import com.example.demo.factory.milk.Milk;

public interface AbstractFactory {
    public Cars getCars(String carsType);

    public Milk getMilk(String milkType);
}
