package com.example.demo.adapter;

public interface ILogin {
    ResultMsg register(String userName,String pwd);

    ResultMsg login(String userName,String pwd);
}
