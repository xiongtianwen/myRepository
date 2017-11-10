package com.xtwService.mapper;


import com.xtw.domain.UserBo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    /**查询列表记录数
     * @param bo
     * @Description: 获取公告条数
     */
    Long queryCount(UserBo bo);

    /**查询用户列表
     * @param bo
     * @Description:
     */
    List<UserBo> queryList(UserBo bo);

    /**
     * 查询用户个人信息
     * @param id
     * @return
     */
    UserBo queryUserObj(int id);

    /**
     * 修改用户个人信息
     * @param bo
     * @return
     */
    Long updateUserInfo(UserBo bo);

    /**
     * 删除用户
     * @param id
     * @return
     */
    Long deleteUser(int id);


}
