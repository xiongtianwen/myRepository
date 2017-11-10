package com.xtw.service;


import com.xtw.common.PageInfo;
import com.xtw.domain.UserBo;

/**
 * Class Name : UserService.
 * Description : 更新价格、备注service接口类.
 * Created by Jhony Zhang on 2016-03-21.
 */
public interface UserService {

    /**
     * 查询公告列表
     *
     * @param bo 公告查询条件
     * @return
     */
    PageInfo queryUserList(UserBo bo) throws Exception;


}
