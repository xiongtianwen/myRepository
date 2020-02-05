package aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class AopCglibProxy implements AopProxy,MethodInterceptor{

    private Object target;
    private AopProxyConfig aopProxyConfig;

    public Object getProxy(Object instance){
        this.target = instance;
        Class<?> clazz = instance.getClass();
        return getInstance(clazz);
    }

    public void setConfig(AopProxyConfig aopConfig) {
        this.aopProxyConfig = aopConfig;
    }

    private Object getInstance(Class<?> clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //这里要通过原始的方法去找Method,通过代理的Method是找不到的
        Method m = this.target.getClass().getMethod(method.getName(),method.getParameterTypes());
        if(aopProxyConfig.containsKey(m)){
            AopProxyConfig.ProxyModel proxyModel = aopProxyConfig.get(m);
            Method before = proxyModel.getBeforeMethod();
            Method after = proxyModel.getAfterMethod();
            Object aspect = proxyModel.getAspect();
            if(before != null){
                before.invoke(aspect,objects);
            }
            Object result = methodProxy.invoke(target,objects);
            if(after != null){
                after.invoke(aspect,objects);
            }
            return result;
        }else{
            return methodProxy.invoke(target,objects);
        }
    }
}
