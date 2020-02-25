package com.xtw.rmi.zk;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/24 21:56
 */
public interface ServiceDiscovery {

    /**
     * 根据服务名称获取服务地址
     * @param serviceName
     * @return
     */
    String discovery(String serviceName);
}
