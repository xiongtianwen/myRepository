package com.xtw.rmi.zk.loadBalance;

import java.util.List;

/**
 * @Authoor: xtw
 * @Description: 负载均衡
 * @Date Created in 2020/2/24 21:41
 */
public interface LoadBalance {

    /**
     * 选择服务器
     * @param repos
     * @return
     */
    String selectHost(List<String> repos);
}
