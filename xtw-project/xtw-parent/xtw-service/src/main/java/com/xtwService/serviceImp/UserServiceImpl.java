package com.xtwService.serviceImp;

import com.alibaba.dubbo.config.annotation.Service;
import com.xtw.common.PageInfo;
import com.xtw.domain.UserBo;
import com.xtwService.mapper.UserMapper;

import com.xtw.service.UserService;
//import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@org.springframework.stereotype.Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public PageInfo queryUserList(UserBo bo) {
        PageInfo pageInfo = new PageInfo();
        try {
            //根据条件获取总条数
            Long count = userMapper.queryCount(bo);
            if (count != null && count > 0) {
                List<UserBo> advertVoList = userMapper.queryList(bo);
                if (null != advertVoList) {
                    pageInfo = new PageInfo(bo.getPage(), bo.getRows(), count, advertVoList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pageInfo;
    }

}
