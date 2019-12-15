package com.example.demo.factory.simple;

import com.example.demo.factory.cars.*;

public class SimpleFactory {
    public Cars getCars(String carType){
        Cars car = null;
        switch (carType){
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
}
