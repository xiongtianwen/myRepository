package com.xtw.spring.demo;

import com.xtw.spring.annotation.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void query() {
        System.out.println("===========================find query function from UserServiceImpl===================");
    }
}
