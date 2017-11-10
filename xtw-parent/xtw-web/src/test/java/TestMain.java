
import com.alibaba.dubbo.config.annotation.Reference;
import com.xtw.common.PageInfo;
import com.xtw.domain.UserBo;
import com.xtw.service.UserService;

public class TestMain {

    @Reference
    private static UserService userService;

    public static void main(String[] args) {
        UserBo bo = new UserBo();
        bo.setUserName("xiongtianwen");
        try {
            PageInfo pageInfo = userService.queryUserList(bo);
            System.out.println(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
