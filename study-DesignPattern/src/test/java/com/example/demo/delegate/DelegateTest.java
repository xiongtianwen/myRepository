package com.example.demo.delegate;

/**
 * 委派模式
 */
public class DelegateTest {
    public static void main(String[] args) {
        Boss boss = new Boss(new TeamLeader());
        boss.doProject();
    }

}
