package aop;

public class AopProxyFactory {

    public static AopProxy getProxyInstance(AopProxyStrategy proxyStrategy){
        System.out.println("invoke AopProxyFactory.getProxyInstance()");
        try {
            Class<?> proxyClass = proxyStrategy.getProxyClass();
            //判断proxyClass是否是AopProxy.class的子类
            if(AopProxy.class.isAssignableFrom(proxyClass)){
                AopProxy proxyInstance = (AopProxy)proxyClass.newInstance();
                return proxyInstance;
            }else{
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        AopProxy aopProxy = AopProxyFactory.getProxyInstance(AopProxyStrategy.JDK_PROXY);
        System.out.println(aopProxy.getClass());
    }
}
