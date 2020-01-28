package com.xtw.spring.demo;

import com.xtw.spring.annotation.Autowired;
import com.xtw.spring.annotation.Controller;
import com.xtw.spring.annotation.RequestMapping;

@Controller("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/query")
    public void query(){
        userService.query();
    }

}
