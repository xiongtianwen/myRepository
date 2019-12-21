package com.example.demo.delegate;

import java.util.HashMap;
import java.util.Map;

public class TeamLeader {

    private Map<String,Staff> staffMap = new HashMap<>();

    public TeamLeader(){
        System.out.println("我是项目经理，开始计划安排任务");
        staffMap.put("登录",new StaffWzp());
        staffMap.put("选题",new StaffYk());
        staffMap.put("产品",new StaffXtw());
    }


    public void doProject(){
        System.out.println("我是项目经理，开始按照计划分配任务");
        for(Map.Entry<String,Staff> entry:staffMap.entrySet()){
                entry.getValue().doModual(entry.getKey());
        }
    }
}
