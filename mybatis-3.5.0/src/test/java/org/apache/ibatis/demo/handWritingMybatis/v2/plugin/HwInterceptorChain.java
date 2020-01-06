package org.apache.ibatis.demo.handWritingMybatis.v2.plugin;

import java.util.ArrayList;
import java.util.List;

public class HwInterceptorChain {
    List<HwInterceptor> interceptors = new ArrayList<>();

    public void addPlugin(HwInterceptor interceptor){
        interceptors.add(interceptor);
    }

    public Object pluginAll(Object target){
        for(HwInterceptor interceptor : interceptors){
            target = interceptor.plugin(target);
        }
        return target;
    }

}
