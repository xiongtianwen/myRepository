package com.example.demo.factory.abstractFactroy;

public class AbstractFactroyTest {

    public static void main(String[] args) {
//        AbstractFactory carsAbstractFactroy = new CarsAbstractFactroy();
//        AbstractFactory milkAbstractFactory = new MilkAbstractFactory();
//        String carsClassName = "com.example.demo.factory.abstractFactroy.CarsAbstractFactroy";
//        String milkClassName = "com.example.demo.factory.abstractFactroy.MilkAbstractFactory";
//        AbstractFactory carsAbstractFactroy = null;
//        AbstractFactory milkAbstractFactory = null;
//        try {
//            carsAbstractFactroy = (AbstractFactory)Class.forName(carsClassName).newInstance();
//            milkAbstractFactory = (AbstractFactory)Class.forName(milkClassName).newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(carsAbstractFactroy.getCars("baoma").getName());
//        System.out.println(milkAbstractFactory.getMilk("yili").getName());
        AbstractSimpleFactory simpleFactory = new AbstractSimpleFactory();
        String name = simpleFactory.getProductName("milk","yili");
        System.out.println(name);
    }


}
