package com.xtw.dubbo;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class TestMock implements HelloService{

    @Override
    public String sayHello(String s) {
        return "系统繁忙："+s;
    }
}
