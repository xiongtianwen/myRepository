package com.xtw.spring.v2.webmvc.servlet;

import com.xtw.spring.v2.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    private final String SERVLET_PARAM = "contextConfigLocation";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("======================调用doGet()===================");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("======================调用doPost()==================");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        new ApplicationContext(config.getInitParameter(SERVLET_PARAM));
    }
}
