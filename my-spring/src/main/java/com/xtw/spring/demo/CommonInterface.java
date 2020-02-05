package com.xtw.spring.demo;

import com.xtw.spring.v2.webmvc.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommonInterface {
    ModelAndView simpleQuery();

    ModelAndView query(HttpServletRequest request, HttpServletResponse response, String id, String name);
}
