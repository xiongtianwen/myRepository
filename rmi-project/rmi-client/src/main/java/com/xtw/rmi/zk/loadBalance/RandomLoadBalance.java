package com.xtw.rmi.zk.loadBalance;

import java.util.List;
import java.util.Random;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/24 21:48
 */
public class RandomLoadBalance extends AbstractLoadBalance {
    @Override
    protected String doSelectHost(List<String> repos) {
        int len = repos.size();
        Random random = new Random();
        return repos.get(random.nextInt(len));
    }
}
