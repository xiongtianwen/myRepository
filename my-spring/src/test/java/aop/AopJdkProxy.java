package aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 默认用jdk动态代理
 */
public class AopJdkProxy implements AopProxy,InvocationHandler {

    private Object target;
    private AopProxyConfig aopProxyConfig;

    public Object getProxy(Object instance){
        this.target = instance;
        Class<?> clazz = instance.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //这里要通过原始的方法去找Method,通过代理的Method是找不到的
        Method m = this.target.getClass().getMethod(method.getName(),method.getParameterTypes());
        if(aopProxyConfig.containsKey(m)){
            AopProxyConfig.ProxyModel proxyModel = aopProxyConfig.get(m);
            Method before = proxyModel.getBeforeMethod();
            Method after = proxyModel.getAfterMethod();
            Object aspect = proxyModel.getAspect();
            if(before != null){
                before.invoke(aspect,args);
            }
            Object result = method.invoke(target,args);
            if(after != null){
                after.invoke(aspect,args);
            }
            return result;
        }else{
            return method.invoke(target,args);
        }
    }

    public void setConfig(AopProxyConfig aopConfig) {
        this.aopProxyConfig = aopConfig;
    }
}
