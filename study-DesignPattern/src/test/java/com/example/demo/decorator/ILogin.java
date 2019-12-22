package com.example.demo.decorator;

public interface ILogin {
    ResultMsg register(String userName, String pwd);

    ResultMsg login(String userName, String pwd);
}
