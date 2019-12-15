package com.example.demo.factory.abstractFactroy;

public class AbstractSimpleFactory {

    private final String CARS_FACTORY_CLASS = "com.example.demo.factory.abstractFactroy.CarsAbstractFactroy";
    private final String MILK_FACTORY_CLASS = "com.example.demo.factory.abstractFactroy.MilkAbstractFactory";

    public String getProductName(String factoryType,String productType){
        String name = null;
        try{
            AbstractFactory factory;
            switch (factoryType){
                case "cars":
                    factory= (AbstractFactory)Class.forName(CARS_FACTORY_CLASS).newInstance();
                    name = factory.getCars(productType).getName();
                    break;
                case "milk":
                    factory = (AbstractFactory)Class.forName(MILK_FACTORY_CLASS).newInstance();
                    name = factory.getMilk(productType).getName();
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return name;

    }
}
