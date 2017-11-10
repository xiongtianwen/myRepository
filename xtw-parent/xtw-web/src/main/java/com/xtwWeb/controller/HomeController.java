package com.xtwWeb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    /**
     * 首页
     */
    @RequestMapping(value = "/")
    public String init1() {
        return "index";
    }

    /**
     * 首页
     */
    @RequestMapping(value = "/index")
    public String init2() {
        return "index";
    }

    /**
     * 关于
     */
    @RequestMapping(value = "/about")
    public String about() {
        return "about";
    }

    /**
     * 博客
     */
    @RequestMapping(value = "/blog")
    public String blog() {
        return "blog";
    }

    /**
     * 联系
     */
    @RequestMapping(value = "/contact")
    public String contact() {
        return "contact";
    }


}

