package com.example.demo.factory.abstractFactroy;

import com.example.demo.factory.cars.Aodi;
import com.example.demo.factory.cars.Baoma;
import com.example.demo.factory.cars.Benchi;
import com.example.demo.factory.cars.Cars;
import com.example.demo.factory.milk.Milk;

public class CarsAbstractFactroy implements AbstractFactory {
    @Override
    public Cars getCars(String carsType) {
        Cars car = null;
        switch (carsType){
            case "baoma":
                car = new Baoma();
                break;
            case "aodi":
                car = new Aodi();
                break;
            case "benchi":
                car = new Benchi();
                break;
        }
        return car;
    }

    @Override
    public Milk getMilk(String milkType) {
        return null;
    }
}
