package com.xtw.spring.demo;

import com.xtw.spring.annotation.Autowired;
import com.xtw.spring.annotation.Controller;
import com.xtw.spring.annotation.RequestMapping;
import com.xtw.spring.annotation.RequestParam;
import com.xtw.spring.v2.webmvc.ModelAndView;
import com.xtw.spring.v3.core.BeanFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController implements CommonInterface {
    @Autowired
    private UserService userService;

    @RequestMapping("/query")
    @Override
    public ModelAndView simpleQuery(){
        userService.query();
        return null;
    }

    @RequestMapping("/queryWithParameter")
    @Override
    public ModelAndView query(HttpServletRequest request, HttpServletResponse response, @RequestParam("id")String id,@RequestParam("name")String name){
        Map<String,String> model = new HashMap<>();
        model.put("name",name);
        model.put("id",id);
        ModelAndView mv = new ModelAndView("index.html",model);
        System.out.println("invoke queryWithParameter method");
        return mv;
    }


}
