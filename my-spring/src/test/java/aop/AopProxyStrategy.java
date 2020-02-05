package aop;

//todo 由于未知原因 在servlet中AopProxyStrategy.JDK_PROXY这句代码不会执行,bug到这一句后再往下就不执行了，但是在main方法中是好的
public enum AopProxyStrategy {
    JDK_PROXY(AopJdkProxy.class),CGLIB_PROXY(AopCglibProxy.class);

    private Class<?> proxy;

    AopProxyStrategy(Class<?> proxy){
        this.proxy = proxy;
    }

    public Class<?> getProxyClass(){
        return this.proxy;
    }
}
