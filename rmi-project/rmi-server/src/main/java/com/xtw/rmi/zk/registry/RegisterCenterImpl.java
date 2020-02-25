package com.xtw.rmi.zk.registry;

import com.xtw.rmi.zk.config.ZkConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/24 20:49
 */
public class RegisterCenterImpl implements RegisterCenter {

    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZkConfig.CONNECTION_STR)
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000,10))
                .build();
        curatorFramework.start();
    }

    @Override
    public void register(String serviceName, String serviceAddress) {
        try {
            //注册的服务名称
            String servicePath = ZkConfig.ZK_REGISTRY_ROOT_PATH + "/" + serviceName;
            //判断name在zk中是否存在，不存在则创建
            //PERSISTENT: 客户端断开连接后，节点不会自动删除。(持久化节点)
            //EPHEMERAL: 客户端断开连接后，节点会自动删除。(临时节点)
            if(curatorFramework.checkExists().forPath(servicePath) == null){
                curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                        .forPath(servicePath,"0".getBytes());
            }
            String addressPath = servicePath + "/" + serviceAddress;
            String rsNode = curatorFramework.create().withMode(CreateMode.EPHEMERAL)
                    .forPath(addressPath,"0".getBytes());
            System.out.println("注册服务成功：" + rsNode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
