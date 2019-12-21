package com.example.demo.delegate;

public class StaffXtw implements Staff {
    private String name = "XTW";
    @Override
    public void doModual(String modual) {
        System.out.println("我是员工："+name);
        System.out.println("开始做"+modual+"模块");
    }
}
