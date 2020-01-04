package org.apache.ibatis.demo.HandwritingMybatisV1;

import org.apache.ibatis.demo.UserDto;

public interface HwUserMapper {

    UserDto selectByPrimaryKey(String id);
}
