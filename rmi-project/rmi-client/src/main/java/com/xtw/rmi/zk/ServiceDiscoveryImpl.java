package com.xtw.rmi.zk;

import com.xtw.rmi.zk.config.ZkConfig;
import com.xtw.rmi.zk.loadBalance.LoadBalance;
import com.xtw.rmi.zk.loadBalance.RandomLoadBalance;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;

/**
 * @Authoor: xtw
 * @Description:
 * @Date Created in 2020/2/24 21:57
 */
public class ServiceDiscoveryImpl implements ServiceDiscovery {

    List<String> repos = new ArrayList<String>();
    private CuratorFramework curatorFramework;

    public ServiceDiscoveryImpl(String address){
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(address)
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000,10))
                .build();
        curatorFramework.start();
    }

    @Override
    public String discovery(String serviceName) {
        try {
            String path =ZkConfig.ZK_REGISTRY_ROOT_PATH + "/" +serviceName;
            repos = curatorFramework.getChildren().forPath(path);
            //动态发现服务节点的变化
            registerWatcher(path);
            //负载均衡
            LoadBalance loadBalance = new RandomLoadBalance();
            return loadBalance.selectHost(repos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void registerWatcher(final String path) {
        PathChildrenCache childrenCache=new PathChildrenCache
                (curatorFramework,path,true);

        PathChildrenCacheListener pathChildrenCacheListener=new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                repos=curatorFramework.getChildren().forPath(path);
            }
        };
        childrenCache.getListenable().addListener(pathChildrenCacheListener);
        try {
            childrenCache.start();
        } catch (Exception e) {
            throw new RuntimeException("注册PatchChild Watcher 异常"+e);
        }

    }
}
