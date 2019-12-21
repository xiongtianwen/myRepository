package com.example.demo.delegate;

public class Boss {
    private TeamLeader teamLeader;

    public Boss(TeamLeader teamLeader){
        System.out.println("我是老板，我手下有个项目经理");
        this.teamLeader = teamLeader;
    }

    public void doProject(){
        System.out.println("我是老板，有个项目要做，全权委托给项目经理");
        teamLeader.doProject();
    }
}
