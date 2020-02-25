package com.xtw.rmi.zk.loadBalance;

import java.util.List;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/24 21:44
 */
public abstract class AbstractLoadBalance implements LoadBalance {
    @Override
    public String selectHost(List<String> repos) {
        if(repos == null || repos.size() == 0){
            return null;
        }else if(repos.size() == 1){
            return repos.get(0);
        }else{
           return doSelectHost(repos);
        }
    }

    protected abstract String doSelectHost(List<String> repos);
}
