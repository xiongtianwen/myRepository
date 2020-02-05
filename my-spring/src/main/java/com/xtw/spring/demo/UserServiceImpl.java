package com.xtw.spring.demo;

import com.xtw.spring.annotation.RequestParam;
import com.xtw.spring.annotation.Service;
import com.xtw.spring.v2.webmvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService,CommonInterface {
    @Override
    public void query() {
        System.out.println("===========================find query function from UserServiceImpl===================");
    }

    @Override
    public ModelAndView simpleQuery() {
        System.out.println("------------------invoke UserServiceImpl.simpleQuery()---------------");
        return null;
    }

    @Override
    public ModelAndView query(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id, @RequestParam("name")String name) {
        System.out.println("------------------invoke UserServiceImpl.query()---------------");
        return null;
    }
}
