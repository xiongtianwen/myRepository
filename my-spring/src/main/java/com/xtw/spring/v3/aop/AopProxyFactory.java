package com.xtw.spring.v3.aop;

public class AopProxyFactory {

    public static AopProxy getProxyInstance(AopProxyStrategy proxyStrategy){
        try {
           String proxyName = proxyStrategy.getProxyName();
           String proxyClassName = proxyStrategy.getClass().getPackage().getName() + "." + proxyName;
           //todo 和AopProxyStrategy.JDK_PROXY一样的问题，这里传入AopProxyStrategy.JDK_PROXY是好的
            //todo ,但是传入AopProxyStrategy.CGLIB_PROXY下面这一行代码就会卡住,会报NoClassDefFound:net.sf.cglib.proxy.MethodInterceptor
            //todo 引入asm包还是报错
           Class<?> proxyClass = Class.forName(proxyClassName);
            AopProxy proxyInstance =  null;
            //判断proxyClass是否是AopProxy.class的子类
            if(AopProxy.class.isAssignableFrom(proxyClass)){
                 proxyInstance = (AopProxy)proxyClass.newInstance();
            }
            return proxyInstance;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        AopProxy aopProxy = AopProxyFactory.getProxyInstance(AopProxyStrategy.CGLIB_PROXY);
        System.out.println(aopProxy.getClass());
    }
}
