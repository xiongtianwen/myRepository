package org.apache.ibatis.demo.handWritingMybatis.v1;

import org.apache.ibatis.demo.UserDto;

public interface HwUserMapper {

    UserDto selectByPrimaryKey(String id);
}
