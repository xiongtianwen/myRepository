package com.xtwWeb.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.xtw.common.PageInfo;
import com.xtw.domain.UserBo;
import com.xtw.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.Map;

@Component
@RequestMapping(value = "/userController")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Reference
    private UserService userService;


    /**
     * 查询用户列表
     * @param bo
     * @return
     */
    @RequestMapping(value = "/queryUserList", method = RequestMethod.POST)
    @ResponseBody
    public Map queryUserList(@RequestBody UserBo bo){
        Map resultMap = new HashMap();
        try {
            PageInfo pageInfo = userService.queryUserList(bo);
            if (null !=pageInfo){
                LOGGER.info("========================查询成功！！！=============================");
                resultMap.put("data",pageInfo);
                resultMap.put("status","successful!!");
            }else{
                resultMap.put("status","failure");
                LOGGER.info("========================查询失败,暂无数据！！！=============================");

            }
        } catch (Exception e) {
            resultMap.put("status","Exception："+e.getMessage());
            e.printStackTrace();
            LOGGER.info("========================系统异常！！！=============================",e.getMessage());
        }

        return  resultMap;
    }

}

