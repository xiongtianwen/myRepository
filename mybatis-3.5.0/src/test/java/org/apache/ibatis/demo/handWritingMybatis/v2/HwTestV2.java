package org.apache.ibatis.demo.handWritingMybatis.v2;

import org.apache.ibatis.demo.UserDto;

public class HwTestV2 {
    public static void main(String[] args) {
        HwSqlsession sqlsession = new HwSqlsession(new HwConfiguration(),new SimpleExecutor());
        UserMapper mapper = sqlsession.getMapper(UserMapper.class);
        UserDto user = mapper.selectByPrimaryKey("2");
        System.out.println(user.toString());
    }
}
