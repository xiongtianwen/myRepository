package com.xtw.rmi.zk.registry;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/24 20:47
 */
public interface RegisterCenter {

    /**
     *  注册
      * @param serviceName 服务名称
     * @param serviceAddress 服务地址
     */
    void register(String serviceName,String serviceAddress);
}
