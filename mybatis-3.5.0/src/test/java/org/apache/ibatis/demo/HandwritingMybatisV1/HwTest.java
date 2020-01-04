package org.apache.ibatis.demo.HandwritingMybatisV1;

import org.apache.ibatis.demo.UserDto;

public class HwTest {
    public static void main(String[] args) {
        HwConfiguration configuration = new HwConfiguration();
        HwExecutor executor = new SimpeHwExecutor();
        HwSqlSession sqlSession = new HwSqlSession(configuration,executor);
        HwUserMapper mapper = sqlSession.getMapper(HwUserMapper.class);
        UserDto userDto = mapper.selectByPrimaryKey("2");
        System.out.println(userDto.toString());
    }
}
