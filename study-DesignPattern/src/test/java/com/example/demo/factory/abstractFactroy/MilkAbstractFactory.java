package com.example.demo.factory.abstractFactroy;

import com.example.demo.factory.cars.Cars;
import com.example.demo.factory.milk.Mengniu;
import com.example.demo.factory.milk.Milk;
import com.example.demo.factory.milk.Yili;

public class MilkAbstractFactory implements AbstractFactory {
    @Override
    public Cars getCars(String carsType) {
        return null;
    }

    @Override
    public Milk getMilk(String milkType) {
        Milk milk = null;
        switch (milkType){
            case "mengnniu":
                milk = new Mengniu();
                break;
            case "yili":
                milk = new Yili();
                break;
        }
        return milk;
    }
}
