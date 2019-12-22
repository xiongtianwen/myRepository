package com.example.demo.adapter;

/**
 * 适配器模式测试
 */
public class LoginTest {
    public static void main(String[] args) {
//        Login login = new Login();
//        ResultMsg resultMsg = login.register("zhangsan","123456");
        ThirdLogin login = new ThirdLogin();
        ResultMsg resultMsg = login.loginForQQ("zhangsan");
        System.out.println(resultMsg.getMsg());
        User u = (User)resultMsg.getData();
        System.out.println(u.getUserName());

    }

}
