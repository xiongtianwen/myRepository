package com.xtwService.config;

import com.xtw.common.PageInfo;
import com.xtw.domain.UserBo;
import com.xtw.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Configuration
@EnableScheduling
public class ScheduledTasks {

    private UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);


    /**
     * 十分钟执行一次
     */
    @Scheduled(fixedRate = 1000 * 5)
    public void getUserName() throws Exception {
    PageInfo pageInfo= userService.queryUserList(new UserBo());
        List list=pageInfo.getList();
        UserBo bo =new UserBo();
        if(null != list && list.size()>0)bo = (UserBo)list.get(0);
        if(null != bo){
            LOGGER.info("=================定时器获取到的用户名称为："+bo.getUserName()+"=======================");
        }else {
            LOGGER.info("=================暂无用户数据=======================");
        }

    }

}
